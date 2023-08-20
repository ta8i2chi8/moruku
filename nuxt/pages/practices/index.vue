<template>
  <div>
    <div class="form-wrapper">
      <div>
        <p class="search-label">都道府県</p>
        <v-select
          class="pref-select input"
          v-model="selectedPrefecture"
          label="選択してください"
          :items="PREFECTURE_LIST"
          variant="outlined"
          density="compact"
          single-line
          hide-details
        ></v-select>
      </div>

      <div>
        <p class="search-label">開催日</p>
        <Datepicker
          class="datepicker input" 
          v-model="selectedStartDate" 
          :enable-time-picker="false"
          format="yyyy/MM/dd"
        />
        <span class="from-to">〜</span>
        <Datepicker 
          class="datepicker input" 
          v-model="selectedEndDate" 
          :enable-time-picker="false"
          format="yyyy/MM/dd"
        />
      </div>

      <div>
        <p class="search-label">キーワード</p>
        <v-text-field 
          class="keyword input"
          v-model="inputtedKeyword"
          variant="outlined"
          density="compact"
          hide-details
        ></v-text-field>      
      </div>

      <v-btn class="search-btn" color="grey" @click="onClickSearch">
        検索
      </v-btn>
    </div>

    <div class="practice-wrapper">
      <div class="practice-wrapper-top">
        <h2 class="practice-title">新着の練習会<span class="sub-title">~練習会や練習試合に参加してみよう~</span></h2>
        <v-btn class="hold-btn" color="green" @click="navigateTo('/practices/create')">
          練習会を開催する
        </v-btn>
      </div>
      
      <p v-if="practices.length === 0" class="no-results">練習会がありません😭</p>
      <ul v-else class="card-list">
        <li v-for="practice in practices" :key="practice.uuid">
          <PracticeCard :practice="practice"/>
        </li>
      </ul>
    </div>
  </div>
</template>

<script setup lang="ts">
import Datepicker from '@vuepic/vue-datepicker';
import '@vuepic/vue-datepicker/dist/main.css';
import { PracticeRepositoryImpl } from '@/domain/repositories/practice-repository';

const practiceRepository = new PracticeRepositoryImpl();

const PREFECTURE_LIST = ["選択してください","北海道","青森県","岩手県","宮城県","秋田県","山形県","福島県","茨城県","栃木県","群馬県","埼玉県","千葉県","東京都","神奈川県","新潟県","富山県","石川県","福井県","山梨県","長野県","岐阜県","静岡県","愛知県","三重県","滋賀県","京都府","大坂府","兵庫県","奈良県","和歌山県","鳥取県","島根県","岡山県","広島県","山口県","徳島県","香川県","愛媛県","高知県","福岡県","佐賀県","長崎県","熊本県","大分県","宮崎県","鹿児島県","沖縄県"];

const selectedPrefecture = ref("選択してください");
const selectedStartDate: Ref<Date | null> = ref(new Date());
const selectedEndDate: Ref<Date | null> = ref(null);
const inputtedKeyword = ref("");


let practices = ref(await practiceRepository.getPractices());

const onClickSearch = async () => {
  console.log(selectedPrefecture.value);
  console.log(getPrefectureId(selectedPrefecture.value));
  console.log("-----");
  console.log(selectedStartDate.value);
  console.log(selectedStartDate.value?.toLocaleDateString("ja-JP", {
        year: "numeric",
        month: "2-digit",
        day: "2-digit",
      }).split("/")
        .join("-") 
    ?? null);
  console.log("-----");
  console.log(selectedEndDate.value);
  console.log(selectedEndDate.value?.toLocaleDateString("ja-JP", {
        year: "numeric",
        month: "2-digit",
        day: "2-digit",
      }).split("/")
        .join("-")
    ?? null);
  console.log("-----");
  console.log(inputtedKeyword.value);
  console.log("-----");

  practices.value = await practiceRepository.searchPractices(
    getPrefectureId(selectedPrefecture.value),
    selectedStartDate.value?.toLocaleDateString("ja-JP", {
        year: "numeric",
        month: "2-digit",
        day: "2-digit",
      }).split("/")
        .join("-") 
    ?? null,
    selectedEndDate.value?.toLocaleDateString("ja-JP", {
        year: "numeric",
        month: "2-digit",
        day: "2-digit",
      }).split("/")
        .join("-")
    ?? null,
    inputtedKeyword.value,
  );
};
</script>

<style scoped>
.form-wrapper {
  margin: 30px auto;
  padding: 15px 30px;
  width: 80%;
  max-width: 1000px;
  min-width: 350px;
  border: 1px solid #aaa;
  border-radius: 10px;
}

.search-label {
  font-size: 14px;
}

.pref-select {
  width: 190px;
}

.datepicker {
  width: 160px;
  display: inline-block;
}

.from-to {
  margin: 0px 10px;
}

.keyword {
  width: 100%;
}

.input {
  margin-bottom: 10px;
}

.search-btn {
  display: block;
  margin: 0px auto;
}

.practice-wrapper-top {
  margin: 0px auto;
  width: 80%;
  max-width: 1000px;
  min-width: 350px;
}

.practice-title {
  display: block;
  margin-top: 30px;
  text-align: center;
}

.sub-title {
  margin-left: 30px;
  font-size: 15px;
  font-weight: normal;
}

.hold-btn {
  display: block;
  margin-left: auto;
}

.no-results {
  margin: 15px 0px 40px 0px;
  text-align: center;
}

.card-list {
  margin: 15px 0px 40px 0px;
}
</style>