<template>
  <div class="profile-wrapper">
    <div class="profile">
      <v-img
      class="profile-icon"
      :width="120"
      :aspect-ratio="1/1"
      cover
      :src="iconUrl"
      ></v-img>
      <p class="profile-nickname">{{ me.nickname }}</p>
    </div>

    <div class="tabs-wrapper">
      <v-tabs class="tabs" v-model="tabIndex" align-tabs="center">
        <v-tab href="#held-practice">開催した練習会</v-tab>
        <v-tab href="#joined-practice">申し込んだ練習会</v-tab>
        <v-tab href="#posted-molkky-activity">投稿したモル活</v-tab>
        <v-tab href="#liked-molkky-activity">いいねしたモル活</v-tab>
      </v-tabs>
      <v-window v-model="tabIndex">
        <v-window-item value="held-practice" :transition="false" :reverse-transition="false">
          <p v-if="heldPractices.length === 0" class="no-results">開催した練習会がありません😭</p>
          <ul v-else class="card-list">
            <li v-for="practice in heldPractices" :key="practice.uuid">
              <PracticeCard :practice="practice"/>
            </li>
          </ul>
        </v-window-item>
        <v-window-item value="joined-practice" :transition="false" :reverse-transition="false">
          <p v-if="joindedPractices.length === 0" class="no-results">申し込んだ練習会がありません😭</p>
          <ul v-else class="card-list">
            <li v-for="practice in joindedPractices" :key="practice.uuid">
              <PracticeCard :practice="practice"/>
            </li>
          </ul>
        </v-window-item>
        <v-window-item value="posted-molkky-activity" :transition="false" :reverse-transition="false">
          <p v-if="molkkyActivities.length === 0" class="no-results">自分の投稿がありません😭</p>
          <ul v-else class="card-list">
            <li v-for="molkkyActivity in molkkyActivities" :key="molkkyActivity.uuid">
              <MolkkyActivityCard :molkkyActivity="molkkyActivity"/>
            </li>
          </ul>
        </v-window-item>
        <v-window-item value="liked-molkky-activity" :transition="false" :reverse-transition="false">
          <p v-if="molkkyActivities.length === 0" class="no-results">いいねした投稿がありません😭</p>
          <ul v-else class="card-list">
            <li v-for="molkkyActivity in molkkyActivities" :key="molkkyActivity.uuid">
              <MolkkyActivityCard :molkkyActivity="molkkyActivity"/>
            </li>
          </ul>
        </v-window-item>
      </v-window>
    </div>
  </div>
</template>

<script setup lang="ts">
import { UserRepositoryImpl } from '@/domain/repositories/user-repository';
import { PracticeRepositoryImpl } from '@/domain/repositories/practice-repository';
import MolkkyActivity from '@/domain/entitys/molkky-activity';

// definePageMeta({
//   middleware: ['auth']
// });

const userRepository = new UserRepositoryImpl();
const practiceRepository = new PracticeRepositoryImpl();

const me = ref(await userRepository.getMe());
const heldPractices = ref(await practiceRepository.getMyPractices());
const joindedPractices = ref(await practiceRepository.getPractices());
const molkkyActivities: Ref<MolkkyActivity[]> = ref([]);
const tabIndex = ref("held-practice");

const iconUrl = computed(() => {
  return me.value.iconUrl ?? "/user-icon.svg";
});
</script>

<style scoped>
.profile-wrapper {
  margin: 30px 0px 10px;
}

.profile-icon {
  margin: 0px auto;
  border-radius: 20px;
}

.profile-nickname {
  padding: 10px 0px;
  font-size: 20px;
  text-align: center;
}

.profile-nickname {
  margin: 0px auto;
}

.tabs {
  margin-bottom: 10px;
}

.no-results {
  margin: 15px 0px 40px 0px;
  text-align: center;
}
</style>