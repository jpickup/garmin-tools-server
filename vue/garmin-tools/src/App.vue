<script setup>
import FileUpload from './components/FileUpload.vue'
</script>

<template>
  <header>
    <img alt="App logo" class="logo" src="./assets/garmin-logo.png" width="125" height="125" />

    <div class="wrapper">
      <FileUpload />
    </div>

  </header>
</template>

<style scoped>
header {
  line-height: 1.5;
}

.logo {
  display: block;
  margin: 0 auto 2rem;
}

@media (min-width: 1024px) {
  header {
    display: flex;
    place-items: center;
    padding-right: calc(var(--section-gap) / 2);
  }

  .logo {
    margin: 0 2rem 0 0;
  }

  header .wrapper {
    display: flex;
    place-items: flex-start;
    flex-wrap: wrap;
  }
}
</style>
