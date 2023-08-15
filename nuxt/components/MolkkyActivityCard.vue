<template>
  <v-card
    class="mx-auto molkky-activity-card"
    width="80%"
    max-width="1000"
    min-width="350"
    variant="outlined"
    density="compact"
    @click="navigateTo('/practices/' + props.molkkyActivity.uuid)"
  >
    <template #title>
      <UserIcon iconUrl="https://avatars0.githubusercontent.com/u/9064066?v=4&s=460"/>
      {{ props.molkkyActivity.posterId }}
      <v-icon :icon="mdiMapMarker"></v-icon>
    </template>
    
    <template #text>
      {{ props.molkkyActivity.content }}
    </template>

    <v-card-actions>
      <div class="actions-icon">
        <v-icon :icon="mdiMapMarker"></v-icon>
        <span>{{ prefectureName }}</span>
      </div>

      <v-spacer></v-spacer>

      <span>公開日: {{ formatedCreatedAt }}</span>
    </v-card-actions>
  </v-card>
</template>

<script setup lang="ts">
import { mdiMapMarker } from '@mdi/js';
import MolkkyActivity from '@/domain/entitys/molkky-activity';

interface Props {
  molkkyActivity: MolkkyActivity;
}

const props = defineProps<Props>();

const prefectureName = computed(() => getPrefecture(props.molkkyActivity.prefectureId));
const formatedCreatedAt = computed(() => new Date(props.molkkyActivity.createdAt).toLocaleDateString('ja-JP'));
</script>

<style scoped>
.molkky-activity-card {
  border: 1px solid #aaa;
}

.actions-icon {
  margin-left: 8px;
}
</style>