import './assets/main.css'


import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
import { createPinia } from 'pinia';

console.log("Server URI: " + import.meta.env.VITE_BACKEND_URI)
const pinia = createPinia();
const app = createApp(App);

app.use(pinia)
app.use(router)
app.mount('#app')
