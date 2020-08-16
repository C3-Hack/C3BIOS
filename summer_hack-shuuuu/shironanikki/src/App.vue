<template>
  <v-app id="inspire">
    <v-headr>
      <Header />
    </v-headr>
    <v-main>
      <!-- <router-link to="/home">home</router-link><br />
      <router-link to="/about">about</router-link>-->
      <!-- <HelloWorld /> -->
      <v-container class="grey lighten-5">
        <router-view />
      </v-container>
    </v-main>
    <v-footer></v-footer>
  </v-app>
</template>

<script>
// import HelloWorld from "./components/HelloWorld.vue";
import Header from "./components/Header";
// import Calendar from "./components/Calendar"

export default {
  name: "App",

  components: {
    // HelloWorld,
    Header,
    // Calendar,
  },

  data: () => ({
    //
  }),
};
</script>
