<template>
  <v-card
    class="mx-auto practice-card"
    width="80%"
    max-width="1000"
    min-width="350"
    variant="outlined"
    density="compact"
    @click="navigateTo('/practices/' + props.practice.uuid)"
  >
    <template #title>
      {{ props.practice.title }}
    </template>

    <v-card-actions>
      <div class="actions-icon">
        <v-icon :icon="mdiAccount"></v-icon>
        <span>{{ props.practice.maxParticipants }}人</span>
      </div>
      
      <div class="actions-icon">
        <v-icon :icon="mdiMapMarker"></v-icon>
        <span>{{ prefectureName }}</span>
      </div>

      <div class="actions-icon">
        <v-icon :icon="mdiCalendarCheck"></v-icon>
        <span>{{ formatedHeldOn }}</span>
      </div>

      <v-spacer></v-spacer>

      <span>公開日: {{ formatedCreatedAt }}</span>
    </v-card-actions>
  </v-card>
</template>

<script setup lang="ts">
import { mdiAccount } from '@mdi/js';
import { mdiMapMarker } from '@mdi/js';
import { mdiCalendarCheck } from '@mdi/js';
import Practice from '@/domain/entitys/practice';

interface Props {
  practice: Practice;
}

const props = defineProps<Props>();

const prefectureName = computed(() => getPrefecture(props.practice.prefectureId));
const formatedHeldOn = computed(() => new Date(props.practice.heldOn).toLocaleDateString('ja-JP'));
const formatedCreatedAt = computed(() => new Date(props.practice.createdAt).toLocaleDateString('ja-JP'));
</script>

<style scoped>
.practice-card {
  border: 1px solid #aaa;
}

.actions-icon {
  margin-left: 8px;
}
</style>