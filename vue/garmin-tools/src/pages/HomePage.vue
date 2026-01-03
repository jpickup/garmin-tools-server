<template>
    <full-page title="Garmin Tools">
    <div class="home-page">
        <div class="home-page-text">
        <p>
          A collection of conversion tools for Garmin watches.
        </p>
          <ul>
            <li><router-link to="/excel2fit" class="navigation-link">Excel to FIT</router-link></li>
            <li><router-link to="/excel2ical" class="navigation-link">Excel to iCal</router-link></li>
            <li><router-link to="/gpx2fit" class="navigation-link">GPX to FIT</router-link></li>
          </ul>
        </div>
    </div>
    </full-page>
</template>

<script>

import FullPage from '@/components/page/FullPage.vue';

export default {
    name: 'HomePage',
    components: {
        FullPage
    },
};
</script>

<style scoped>
p {
    text-align: left;
    margin: 10px;
    font-size: 1.5rem;
}

h2 {
    margin: 10px;
    text-align: left;
    font-size: 2.5rem;
}

.home-page {

}

.home-page-text {
    position: relative;
}
.home-page-images {
    /* flex: 1 1; */
    position: relative;
    height: 800px;
}

.image {
    width: 36rem;
    position: absolute;
    top: 0%;
    left: 50%;
    box-shadow: 0 3rem 6rem rgba(0, 0, 0, 0.4);
    border-radius: 1rem;
    border: 0.1rem solid gray;
}

.image-1 {
    transform: translate(-20%, 0%);
    z-index: 0;
}

.image-2 {
    transform: translate(-70%, 60%);
    z-index: 1;
}

.image-3 {
    transform: translate(0%, 120%);
    z-index: 0;
}

.image-4 {
    transform: translate(-40%, 180%);
    z-index: 1;
}
</style>
