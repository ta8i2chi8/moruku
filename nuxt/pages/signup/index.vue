<template>
  <v-container>
    <v-row class="mb-6" no-gutters justify="center">
      <v-col cols="6">
        <h2 class="text-align-center">会員登録</h2>
      </v-col>
    </v-row>

    <!-- Googleで会員登録 -->
    <v-row class="mb-2" no-gutters justify="center">
      <v-col cols="6">
        <h4 class="text-align-center">Googleで登録</h4>
      </v-col>
    </v-row>

    <v-row no-gutters justify="center">
      <v-col cols="6">
        <v-btn
          class="btn"
          color="blue"
          :prepend-icon="mdiGoogle"
          @click="onClickSignUpWithGoogle"
        >
          新規登録
        </v-btn>
      </v-col>
    </v-row>


    <!-- or -->
    <v-row class="my-10" no-gutters justify="center">
      <v-col cols="6">
        <p class="text-align-center">or</p>
      </v-col>
    </v-row>


    <!-- メールアドレスで登録 -->
    <v-row class="mb-2" no-gutters justify="center">
      <v-col cols="6">
        <h4 class="text-align-center">メールアドレスで登録</h4>
      </v-col>
    </v-row>

    <v-row no-gutters justify="center">
      <v-col cols="6">
        <p class="text">ニックネーム（40文字以内）</p>
      </v-col>
    </v-row>
    <v-row no-gutters justify="center">
      <v-col cols="6">
        <v-text-field maxlength="40" density="compact"></v-text-field>
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
          @click="onClickSignUpWithEmail"
        >
          新規登録
        </v-btn>
      </v-col>
    </v-row>
  </v-container>
</template>

<script setup lang="ts">
import { mdiGoogle } from '@mdi/js';
import { mdiEmail } from '@mdi/js';

const inputEmail = ref('');
const inputPassword = ref('');

const { signUpWithGoogle, signUpWithEmail, verifyEmail } = useAuth();

const onClickSignUpWithGoogle = async () => {
  await signUpWithGoogle();

  const to = useRoute().redirectedFrom?.fullPath || '/'
  await navigateTo(to, { redirectCode: 302 });
};

const onClickSignUpWithEmail = async () => {
  await signUpWithEmail(inputEmail.value, inputPassword.value);
  await verifyEmail();
  await navigateTo('/signup/confirm');
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