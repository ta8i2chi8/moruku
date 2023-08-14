<template>
  <v-avatar :size="size">
    <v-img
      v-if="iconUrl"
      alt="user icon"
      :src="iconUrl"
    ></v-img>
    <v-icon
      v-else
      color="grey"
      :size="size"
      :icon="mdiAccountCircle"
    ></v-icon>
  </v-avatar>
</template>

<script setup lang="ts">
import { mdiAccountCircle } from '@mdi/js';

interface Props {
  size?: number;
  iconUrl: string;
}

const props = withDefaults(defineProps<Props>(), {
  size: 30,
  iconUrl: "",
});
</script>