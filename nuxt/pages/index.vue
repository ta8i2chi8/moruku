<template>
  <div>
    <div class="top-wrapper">
      <h1 class="top-title">モルッカーをつなぐプラットフォーム</h1>
      <v-container>
        <v-row>
          <v-col>
            <div class="circle" @click="navigateTo('/practices')">
              <h3>練習会開催・参加</h3>
            </div>
          </v-col>
          <v-col>
            <div class="circle" @click="navigateTo('/molkky_activities')">
              <h3>モル活投稿・情報収集</h3>
            </div>
          </v-col>
        </v-row>
      </v-container>
    </div>

    <v-divider thickness="3" color="grey"></v-divider>
    
    <div class="practice-wrapper">
      <div class="practice-wrapper-top">
        <h2 class="practice-title">新着の練習会<span class="sub-title">~練習会や練習試合に参加してみよう~</span></h2>
        <v-btn class="home-btn" color="green" @click="navigateTo('/practices/create')">
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
    
    <v-divider thickness="3" color="grey"></v-divider>
    
    <div class="molkky-activity-wrapper">
      <div class="molkky-activity-wrapper-top">
        <h2 class="molkky-activity-title">新着のモル活<span class="sub-title">~練習をした記録を投稿してみよう~</span></h2>
        <v-btn class="home-btn" color="green" @click="navigateTo('/molkky_activities/create')">
          モル活を投稿する
        </v-btn>
      </div>
      <p v-if="molkkyActivities.length === 0" class="no-results">投稿がありません😭</p>
      <ul v-else class="card-list">
        <li v-for="molkkyActivity in molkkyActivities" :key="molkkyActivity.uuid">
          <MolkkyActivityCard :molkkyActivity="molkkyActivity"/>
        </li>
      </ul>
    </div>
  </div>
</template>

<script setup lang="ts">
import MolkkyActivity from '@/domain/entitys/molkky-activity';
import { PracticeRepositoryImpl } from '@/domain/repositories/practice-repository';

const practiceRepository = new PracticeRepositoryImpl();

const practices = ref(await practiceRepository.getPractices(3, 0));
const molkkyActivities = ref([
  new MolkkyActivity(
    "asdf",
    "練習メニュー: 12mトレ",
    "asdf",
    13,
    "2023-01-01T10:10:10",
  ),
  new MolkkyActivity(
    "asdf",
    "練習メニュー: 12mトレ",
    "asdf",
    13,
    "2023-01-01T10:10:10",
  ),
]);
</script>

<style scoped>
.top-wrapper {
  text-align: center;
  background-image: url(../assets/image/top-background-image.jpg);
  background-size:  cover;
  background-position: center center; 
}

.top-title {
  padding-top: 15px;
  text-shadow: 1px 2px 3px #808080;
  color: white;
}

.circle {
  width: 200px;
  height: 100px;
  background-color: white;
  border-radius: 50%;
  margin: 0 auto;
  text-align: center;
  color: #444;
  line-height: 100px;
  cursor: pointer;
}

.circle:hover {
  background-color: #eee;
  text-decoration: underline;
}

.practice-wrapper-top,
.molkky-activity-wrapper-top {
  margin: 0px auto;
  width: 80%;
  max-width: 1000px;
  min-width: 350px;
}


.practice-title {
  display: block;
  margin-top: 10px;
  text-align: center;
}

.molkky-activity-title {
  display: block;
  margin-top: 10px;
  text-align: center;
}

.sub-title {
  margin-left: 30px;
  font-size: 15px;
  font-weight: normal;
}

.home-btn {
  display: block;
  margin-left: auto;
}

.card-list {
  margin: 15px 0px 40px 0px;
}

.no-results {
  margin: 15px 0px 40px 0px;
  text-align: center;
}
</style>