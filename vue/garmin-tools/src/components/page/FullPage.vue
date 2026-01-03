<template>
    <div class="full-page">
        <page-header class="full-page-header" :title="title"/>
        <page-body class="full-page-body">
            <slot />
        </page-body>
    </div>
</template>

<script>
import PageHeader from '@/components/page/PageHeader.vue';
import PageBody from '@/components/page/PageBody.vue';


export default {
    name: 'FullPage',
    props: ['title'],
    components: {
        PageHeader,
        PageBody,
    },
};
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
.full-page {
    display: flex;
    flex-direction: column;
    justify-content: space-between;
    align-items: flex-start;
}
.full-page-header {
    flex-grow: 0;
}
.full-page-body {
    flex-grow: 1;
}

</style>
