<template>
  <v-app-bar>
    <template v-slot:prepend>
      <v-app-bar-title class="title" @click="$router.push('/')">モルク</v-app-bar-title>
    </template>
    <v-spacer></v-spacer>
    <template v-slot:append>
      <v-btn variant="text" @click="$router.push('/')">練習会</v-btn>
      <v-btn variant="text" @click="$router.push('/')">モル活</v-btn>
      <v-btn variant="text" @click="$router.push('/signin')">ログイン</v-btn>
      <v-btn variant="text" @click="$router.push('/signup')" v-if="true">会員登録</v-btn>
      <v-btn variant="text" @click="$router.push('/')" v-else>マイページ</v-btn>
    </template>
  </v-app-bar>
</template>

<style scoped>
.title {
  cursor: pointer;
}
</style>