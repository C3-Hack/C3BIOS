/*jshint esversion: 6 */
/*jshint es5: false */
import Vue from 'vue';
import App from './App.vue';
import './registerServiceWorker';
import router from './router';
import store from './store';
import vuetify from './plugins/vuetify';
import firebase from 'firebase';

Vue.config.productionTip = false;

const firebaseConfig = {
  apiKey: "AIzaSyBy9rIObGUg_3l8cV_e_2Ayr-AjA-wfuyI",
  authDomain: "shironanikki-6358.firebaseapp.com",
  databaseURL: "https://shironanikki-6358.firebaseio.com",
  projectId: "shironanikki-6358",
  storageBucket: "shironanikki-6358.appspot.com",
  messagingSenderId: "263763032311",
  appId: "1:263763032311:web:61ad1f20535e15025d3bf2"
};
// Initialize Firebase
firebase.initializeApp(firebaseConfig);

new Vue({
  router,
  store,
  vuetify,
  render: h => h(App)
}).$mount('#app');
