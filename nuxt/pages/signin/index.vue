<template>
  <v-container>
    <v-row class="mb-6" no-gutters justify="center">
      <v-col cols="6">
        <h2 class="text-align-center">ログイン</h2>
      </v-col>
    </v-row>

    <v-row class="mb-6" no-gutters justify="center">
      <v-col cols="6">
        <p class="text-align-center">会員登録されていない方は、こちら<NuxtLink to="/signup">新規会員登録</NuxtLink>へ</p>
      </v-col>
    </v-row>

    <!-- Googleでログイン -->
    <v-row class="mb-2" no-gutters justify="center">
      <v-col cols="6">
        <h4 class="text-align-center">Googleでログイン</h4>
      </v-col>
    </v-row>

    <v-row no-gutters justify="center">
      <v-col cols="6">
        <v-btn
          class="btn"
          color="blue"
          :prepend-icon="mdiGoogle"
          @click="onClickSignInWithGoogle"
        >
          ログイン
        </v-btn>
      </v-col>
    </v-row>


    <!-- or -->
    <v-row class="my-10" no-gutters justify="center">
      <v-col cols="6">
        <p class="text-align-center">or</p>
      </v-col>
    </v-row>


    <!-- メールアドレスでログイン -->
    <v-row class="mb-2" no-gutters justify="center">
      <v-col cols="6">
        <h4 class="text-align-center">メールアドレスでログイン</h4>
      </v-col>
    </v-row>

    <v-row no-gutters justify="center">
      <v-col cols="6">
        <p class="text">メールアドレス</p>
      </v-col>
    </v-row>
    <v-row no-gutters justify="center">
      <v-col cols="6">
        <v-text-field v-model="inputEmail" placeholder="moruku@example.com" density="compact"></v-text-field>
      </v-col>
    </v-row>

    <v-row no-gutters justify="center">
      <v-col cols="6">
        <p class="text">パスワード</p>
      </v-col>
    </v-row>
    <v-row no-gutters justify="center">
      <v-col cols="6">
        <v-text-field v-model="inputPassword" type="password" density="compact"></v-text-field>
      </v-col>
    </v-row>

    <v-row no-gutters justify="center">
      <v-col cols="6">
        <v-btn 
          class="btn" 
          color="red"
          :prepend-icon="mdiEmail"
          @click="onClickSignInWithEmail"
        >
          ログイン
        </v-btn>
      </v-col>
    </v-row>

    <v-row class="mt-4" no-gutters justify="center">
      <v-col cols="6">
        <p class="text-align-center">パスワードを忘れた方は<NuxtLink to="/signin/password_reset">こちら</NuxtLink></p>
      </v-col>
    </v-row>
  </v-container>

  <v-snackbar
    v-model="isDisplaySnackbar"
    timeout="5000"
  >
    未登録のユーザーです。先に会員登録してください
    <template v-slot:actions>
      <v-btn
        color="red"
        variant="text"
        @click="isDisplaySnackbar = false"
      >
        Close
      </v-btn>
    </template>
  </v-snackbar>
</template>

<script setup lang="ts">
import { mdiGoogle } from '@mdi/js';
import { mdiEmail } from '@mdi/js';

definePageMeta({
  middleware: ['auth-signin']
});

const inputEmail = ref('');
const inputPassword = ref('');
const isDisplaySnackbar = ref(false);

const { signInWithGoogle, signInWithEmail } = useAuth();

const onClickSignInWithGoogle = async () => {
  const isSucceeded = await signInWithGoogle();

  if (isSucceeded) {
    const to = useRoute().redirectedFrom?.fullPath || '/'
    await navigateTo(to, { redirectCode: 302 });
  } else {
    isDisplaySnackbar.value = true;
  }
};

const onClickSignInWithEmail = async () => {
  await signInWithEmail(inputEmail.value, inputPassword.value);

  const to = useRoute().redirectedFrom?.fullPath || '/'
  await navigateTo(to, { redirectCode: 302 });
};
</script>

<style scoped>
.text-align-center {
  text-align: center;
}

.btn {
  width: 100%;
  text-transform: none;
  font-weight: bold;
}
</style>