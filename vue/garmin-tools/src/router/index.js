import { createRouter, createWebHashHistory } from 'vue-router'
import HomePage from '../pages/HomePage.vue'
import ExcelToFitPage from '../pages/ExcelToFitPage.vue'
import ExcelToIcalPage from '../pages/ExcelToIcalPage.vue'
import GpxToFitPage from '../pages/GpxToFitPage.vue'

const routes = [
    {
        path: '/',
        name: 'home',
        component: HomePage
    },
    {
        path: '/excel2fit',
        name: 'excel2fit',
        component: ExcelToFitPage
    },
    {
        path: '/excel2ical',
        name: 'excel2ical',
        component: ExcelToIcalPage
    },
    {
        path: '/gpx2fit',
        name: 'gpx2fit',
        component: GpxToFitPage
    },
]

const router = createRouter({
    history: createWebHashHistory(),
    routes
})

export default router
