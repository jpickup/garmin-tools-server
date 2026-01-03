<template>
  <full-page title="Excel to FIT">
    <FileUpload description="Convert an Excel workout to a collection of Garmin FIT workout files, packaged as a ZIP." url="/api/schedule/fit"/>
  </full-page>
</template>

<script>
import FullPage from '@/components/page/FullPage.vue';
import FileUpload from '@/components/FileUpload.vue'

export default {
  name: 'ExcelToFitPage',
  components: {
    FullPage,
    FileUpload
  }
}
</script>

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
</style>>
