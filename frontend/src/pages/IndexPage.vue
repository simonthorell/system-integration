<template>
  <q-page class="row items-center justify-evenly">
    <get-clothes></get-clothes>
  </q-page>
</template>

<script setup lang="ts">
// import { ref } from 'vue';
// import { Todo, Meta } from 'components/models';
import GetClothes from 'src/components/GetClothes.vue';

defineOptions({
  name: 'IndexPage',
});
</script>
