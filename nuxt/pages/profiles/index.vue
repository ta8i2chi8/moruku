<template>
  <div>
    profiles page
    <p>user: {{ user }}</p>
    <p>isAuthed: {{ isAuthed }}</p>
  </div>
</template>

<script setup lang="ts">
// definePageMeta({
//   middleware: ['auth']
// });

const { user, isAuthed } = useAuth();

</script>