<template>
  <div>
    <div class="create-wrapper-top">
      <h2 class="create-title">練習会を開催する</h2>
    </div>
    <div class="create-wrapper">
      <div>
        <p class="create-label">タイトル（100文字以内）</p>
        <v-text-field 
          class="title input"
          v-model="inputtedTitle"
          variant="outlined"
          density="compact"
          hide-details
        ></v-text-field>
      </div>
      
      <div>
        <p class="create-label">練習会詳細（500文字以内）</p>
        <v-textarea
          class="description input"
          v-model="inputtedDescription"
          variant="outlined"
          density="compact"
          rows="3"
          hide-details
        ></v-textarea>
      </div>

      <div>
        <p class="create-label">
          開催場所
          <v-icon :icon="mdiMapMarker"></v-icon>
        </p>
        <v-select
          class="place input"
          v-model="selectedPlace"
          label="選択してください"
          :items="PREFECTURE_LIST"
          variant="outlined"
          density="compact"
          single-line
          hide-details
        ></v-select>
      </div>

      <div>
        <p class="create-label">
          開催日
          <v-icon :icon="mdiCalendarCheck"></v-icon>
        </p>
        <Datepicker
          class="datepicker input" 
          v-model="selectedDate" 
          :enable-time-picker="false"
          format="yyyy/MM/dd"
        />
      </div>

      <div>
        <p class="create-label">
          人数上限
          <v-icon :icon="mdiAccount"></v-icon>
        </p>
        <v-checkbox 
          label="人数上限を設ける"
          v-model="selectedNumPeopleFlag"
          hide-details
        ></v-checkbox>
        <div v-if="selectedNumPeopleFlag">
          <v-text-field 
          class="numPeople input"
          v-model="validatedNumPeople"
          variant="outlined"
          density="compact"
          type="number"
          :min="MIN_NUM_PEOPLE"
          :max="MAX_NUM_PEOPLE"
          hide-details
          ></v-text-field>
          <span class="nin">人</span> 
        </div>
      </div>

      <v-btn class="release-btn" color="green" @click="onClickRelease">
        公開する
      </v-btn>
      <p class="error-message">{{ errorMessage }}</p>
    </div>
  </div>
</template>

<script setup lang="ts">
import Datepicker from '@vuepic/vue-datepicker';
import '@vuepic/vue-datepicker/dist/main.css';
import { mdiAccount } from '@mdi/js';
import { mdiMapMarker } from '@mdi/js';
import { mdiCalendarCheck } from '@mdi/js';
import { PracticeRepositoryImpl } from '@/domain/repositories/practice-repository';

definePageMeta({
  middleware: ['auth']
});

const MIN_NUM_PEOPLE = 1;
const MAX_NUM_PEOPLE = 999;
const PREFECTURE_LIST = ["選択してください","北海道","青森県","岩手県","宮城県","秋田県","山形県","福島県","茨城県","栃木県","群馬県","埼玉県","千葉県","東京都","神奈川県","新潟県","富山県","石川県","福井県","山梨県","長野県","岐阜県","静岡県","愛知県","三重県","滋賀県","京都府","大坂府","兵庫県","奈良県","和歌山県","鳥取県","島根県","岡山県","広島県","山口県","徳島県","香川県","愛媛県","高知県","福岡県","佐賀県","長崎県","熊本県","大分県","宮崎県","鹿児島県","沖縄県"];

const practiceRepository = new PracticeRepositoryImpl();

const inputtedTitle = ref("");
const inputtedDescription = ref("");
const selectedPlace = ref("選択してください");
const selectedDate = ref(new Date());
const selectedNumPeopleFlag = ref(false);
const selectedNumPeople = ref("1");
const errorMessage = ref("");

const validatedNumPeople = computed({
  get: () => selectedNumPeople.value,
  set: (value: string): void => {
    let intValue = parseInt(value);
    if (Number.isNaN(intValue) || intValue < MIN_NUM_PEOPLE) {
      intValue = MIN_NUM_PEOPLE;
    } else if (intValue > MAX_NUM_PEOPLE) {
      intValue = MAX_NUM_PEOPLE;
    }

    selectedNumPeople.value = `${intValue}`;
    console.log(selectedNumPeople.value);
  }
});

const onClickRelease = async () => {
  const { user } = useAuth();

  console.log(inputtedTitle.value);
  console.log(inputtedDescription.value);
  console.log(selectedNumPeopleFlag.value ? parseInt(validatedNumPeople.value) : null);
  console.log(getPrefectureId(selectedPlace.value));
  console.log(user.value);
  console.log(selectedDate.value?.toISOString() ?? null);

  if (inputtedTitle.value === '') {
    errorMessage.value = 'タイトルの入力は必須です';
    return;
  }
  // await practiceRepository.insertPractice({
  //   title: inputtedTitle.value,
  //   description: inputtedDescription.value,
  //   maxParticipants: parseInt(validatedNumPeople.value),
  //   prefectureId: getPrefectureId(selectedPlace.value),
  //   organizerId: 1,
  //   heldOn: selectedDate.value.toLocaleString(),
  // });
};
</script>

<style scoped>
.create-wrapper-top {
  margin: 20px auto;
  width: 80%;
  max-width: 1000px;
  min-width: 350px;
}

.create-wrapper {
  margin: 20px auto;
  padding: 15px 30px;
  width: 80%;
  max-width: 1000px;
  min-width: 350px;
  border: 1px solid #aaa;
  border-radius: 10px;
}

.input {
  margin-bottom: 20px;
}

.place {
  width: 190px;
}

.datepicker {
  width: 160px;
  display: inline-block;
}

.numPeople {
  display: inline-block;
  width: 80px;
}

.nin {
  margin: 10px;
  position: absolute;
}

.release-btn {
  display: block;
  margin: 0px auto;
}

.error-message {
  margin-top: 10px;
  color: red;
  font-weight: bold;
  text-align: center;
}
</style>