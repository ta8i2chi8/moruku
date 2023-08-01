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
          @click=""
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
        <v-text-field placeholder="moruku@example.com" density="compact"></v-text-field>
      </v-col>
    </v-row>

    <v-row no-gutters justify="center">
      <v-col cols="6">
        <p class="text">パスワード</p>
      </v-col>
    </v-row>
    <v-row no-gutters justify="center">
      <v-col cols="6">
        <v-text-field type="password" density="compact"></v-text-field>
      </v-col>
    </v-row>

    <v-row no-gutters justify="center">
      <v-col cols="6">
        <v-btn 
          class="btn" 
          color="red"
          :prepend-icon="mdiEmail"
          @click=""
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
</template>

<script setup lang="ts">
import { mdiGoogle } from '@mdi/js';
import { mdiEmail } from '@mdi/js';
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