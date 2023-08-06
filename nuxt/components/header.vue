<template>
  <v-app-bar>
    <template v-slot:prepend>
      <v-app-bar-title class="title" @click="navigateTo('/')">モルク</v-app-bar-title>
    </template>
    <v-spacer></v-spacer>
    <template v-slot:append>
      <v-btn variant="text" @click="navigateTo('/practices')">練習会</v-btn>
      <v-btn variant="text" @click="navigateTo('/molkky_activities')">モル活</v-btn>
      <v-btn variant="text" @click="navigateTo('/profiles')" v-if="isAuthed">マイページ</v-btn>
      <v-btn variant="text" @click="navigateTo('/signup')" v-else>会員登録</v-btn>
      <v-btn variant="text" @click="onClickSignOut" v-if="isAuthed">ログアウト</v-btn>
      <v-btn variant="text" @click="navigateTo('/signin')" v-else>ログイン</v-btn>
    </template>
  </v-app-bar>

  <v-snackbar
      v-model="isDisplaySnackbar"
      timeout="2000"
    >
      ログアウトしました
      <template v-slot:actions>
        <v-btn
          color="blue"
          variant="text"
          @click="isDisplaySnackbar = false"
        >
          Close
        </v-btn>
      </template>
    </v-snackbar>
</template>

<script setup lang="ts">
const isDisplaySnackbar = ref(false);

const { isAuthed, signOut } = useAuth();

const onClickSignOut = async () => {
  await signOut();
  isDisplaySnackbar.value = true;
  await navigateTo('/');
};
</script>

<style scoped>
.title {
  cursor: pointer;
}
</style>