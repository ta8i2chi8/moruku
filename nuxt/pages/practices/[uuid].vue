<template>
  <div>
    <div class="detail-wrapper">
      <h3 class="detail-title">{{ practice.title }}</h3>

      <div class="detail-contents">
        <p class="detail-description">{{ practice.description }}</p>
        
        <div class="info">
          <v-icon :icon="mdiAccount"></v-icon>
          <span v-if="practice.maxParticipants">{{ practice.maxParticipants }}人</span>
          <span v-else>上限なし</span>
        </div>
        
        <div class="info">
          <v-icon :icon="mdiMapMarker"></v-icon>
          <span v-if="practice.prefectureId">{{ prefectureName }}</span>
          <span v-else>未定</span>
        </div>
        
        <div class="info">
          <v-icon :icon="mdiCalendarCheck"></v-icon>
          <span v-if="practice.heldOn">{{ formatedHeldOn }}</span>
          <span v-else>未定</span>
        </div>
        
        <p class="info release-date">公開日: {{ formatedCreatedAt }}</p>
      </div>
    </div>

    <v-btn class="join-btn" color="green" @click="onClickJoin">
      参加する
    </v-btn>
  </div>

  <v-snackbar
    v-model="isDisplaySnackbar"
    timeout="2000"
  >
    参加の申し込みが完了しました
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
import { mdiAccount } from '@mdi/js';
import { mdiMapMarker } from '@mdi/js';
import { mdiCalendarCheck } from '@mdi/js';
import { PracticeRepositoryImpl } from '@/domain/repositories/practice-repository';

const route = useRoute()
const uuid = route.params.uuid as string;

const practiceRepository = new PracticeRepositoryImpl();
const practice = ref(await practiceRepository.getPracticeById(uuid));
const isDisplaySnackbar = ref(false);

const prefectureName = computed(() => getPrefecture(practice.value.prefectureId));
const formatedHeldOn = computed(() => new Date(practice.value.heldOn).toLocaleDateString('ja-JP'));
const formatedCreatedAt = computed(() => new Date(practice.value.createdAt).toLocaleDateString('ja-JP'));

const onClickJoin = async () => {
  await practiceRepository.joinPractice(practice.value.uuid);
  isDisplaySnackbar.value = true;
};
</script>

<style scoped>
.detail-wrapper {
  margin: 30px auto;
  padding: 15px 30px;
  width: 80%;
  max-width: 1000px;
  min-width: 350px;
  border: 1px solid #aaa;
  border-radius: 10px;
}

.detail-contents {
  margin: 0px 10px;
}

.detail-title {
  margin-bottom: 15px;
  padding-bottom: 5px;
  border-bottom: 1px dotted black;
}

.detail-description {
  margin: 20px 0px;
}

.info {
  margin-bottom: 5px;
  font-size: 14px;
}

.release-date {
  text-align: right;
}

.join-btn {
  display: block;
  margin: 0px auto;
}
</style>