<template>
  <div>
    <div class="form-wrapper">
      <div>
        <p class="search-label">都道府県</p>
        <v-select
          class="pref-select input"
          label="選択してください"
          :items="['選択してください', '東京都', '千葉県']"
          variant="outlined"
          density="compact"
          single-line
          hide-details
        ></v-select>
      </div>

      <div>
        <p class="search-label">開催日</p>
        <Datepicker class="datepicker input" v-model="startDate" :enable-time-picker="false"/>
        <span class="from-to">〜</span>
        <Datepicker class="datepicker input" v-model="endDate" :enable-time-picker="false"/>
      </div>

      <div>
        <p class="search-label">キーワード</p>
        <v-text-field 
          class="keyword input"
          variant="outlined"
          density="compact"
          hide-details
        ></v-text-field>      
      </div>

      <v-btn class="search-btn" color="grey" @click="navigateTo('/practices/create')">
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
const startDate = ref(new Date());
const endDate = ref(new Date());

const practices = await practiceRepository.getPractices();
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
  margin-bottom: 15px;
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
  margin-top: 60px;
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

.card-list {
  margin: 15px 0px 40px 0px;
}
</style>