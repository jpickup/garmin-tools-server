<template>
    <div class="page-header">
        <div class="page-header-padding"></div>
        <div class="page-header-title"><h1>{{ title }}</h1></div>
        <!-- <div class="page-header-logo"><img src="garmin.png" height="48"></div> -->
    </div>
</template>

<script>

export default {
    name: 'PageHeader',
    props: ['title'],
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
.page-header {
    margin-left: 1rem;
    margin-right: 1rem;
    position: fixed;
    z-index: 1;
    width: 100%;
    display: flex;
    justify-content: space-between;
    align-items: center;
    align-content: space-evenly;
}
.page-header-padding {
    width: 2px;
    flex-grow: 0;
}
.page-header-title {
    flex-grow: 1;
    text-align: left;
}

.page-header-logo {
    margin-right: 6rem;
    flex-grow: 0;
    width: 50px;
}

h1 {
    margin: 1rem;
    font-size: 2.0rem;
    font-weight: 800;
}
</style>
