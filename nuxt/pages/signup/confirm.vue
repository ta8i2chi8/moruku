<template>
  <v-container>
    <v-row class="mb-6" no-gutters justify="center">
      <v-col cols="6">
        <h2 class="text-align-center">メールアドレスの確認</h2>
      </v-col>
    </v-row>

    <v-row class="mb-6" no-gutters justify="center">
      <v-col cols="10">
        <p class="text-align-center">
          アカウントを有効化するため、ご登録いただいたメールアドレスに確認リンクを送信しました。
        </p>
        <p class="text-align-center">
          メールをご確認いただき、リンクをクリックしてください。
        </p>
        <p class="text-align-center text-bold">
          リンクをクリック後、下記の確認ボタンをクリックしてください。
        </p>
      </v-col>
    </v-row>
    <v-row class="mb-6" no-gutters justify="center">
      <v-col cols="10">
        <p class="text-align-center">
          ご登録のメールアドレス： {{ user?.email || '' }}
        </p>
      </v-col>
    </v-row>
    <v-row class="mb-6" no-gutters justify="center">
      <v-col cols="10">
        <p class="text-align-center text-bold text-red">
          {{ messege }}
        </p>
      </v-col>
    </v-row>

    <v-row no-gutters justify="center">
      <v-col cols="2" class="mr-1">
        <v-btn 
          class="btn" 
          color="grey"
          @click="resendEmail"
        >
          メールを再送
        </v-btn>
      </v-col>
      <v-col cols="2" class="ml-1">
        <v-btn 
          class="btn" 
          color="blue"
          @click="navigateToHome"
        >
          確認
        </v-btn>
      </v-col>
    </v-row>
  </v-container>

  <v-snackbar
      v-model="isDisplaySnackbar"
      timeout="2000"
    >
      確認メールを再送しました
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
const messege = ref('');

const { user, reloadCurrentUser, verifyEmail } = useAuth();

const resendEmail = async () => {
  await verifyEmail();
  isDisplaySnackbar.value = true;
};

const navigateToHome = async () => {
  await reloadCurrentUser();
  if (user.value?.emailVerified) {
    await navigateTo('/');
    messege.value = '';
  } else {
    messege.value = 'まだメールアドレスの認証が完了していません';
  }
};
</script>

<style scoped>
.text-align-center {
  text-align: center;
}

.text-bold {
  font-weight: bold;
}

.text-red {
  color: red;
}

.btn {
  width: 100%;
  text-transform: none;
  font-weight: bold;
}
</style>