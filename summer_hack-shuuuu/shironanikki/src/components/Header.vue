<template>
  <div>
    <v-system-bar color="FF0000 accent-4" dense dark>
      467nikki
      <!-- <v-spacer></v-spacer>
      <v-icon>mdi-window-minimize</v-icon>
      <v-icon>mdi-window-maximize</v-icon>
      <v-icon>mdi-close</v-icon>-->
    </v-system-bar>
    <v-app-bar color="FF0000 accent-4" dense dark>
      <div v-show="isAuthenticated">
        <v-app-bar-nav-icon @click="drawer = !drawer"></v-app-bar-nav-icon>
      </div>
      <v-toolbar-title>467nikki</v-toolbar-title>
      <v-spacer></v-spacer>
      <div v-if="!isAuthenticated">
        <v-btn color="primary" to="/signin" data-cy="signinBtn">SIGN IN</v-btn>
        <v-btn color="success" to="/signup" class="nav-join" data-cy="joinBtn">JOIN</v-btn>
      </div>
      <div v-else>
        <!-- <v-btn text to="/profile">PROFILE</v-btn>
        <SignoutBtn />
        <v-btn outline color="gray" @click="logout" data-cy="logout">Logout</v-btn> -->
        <!-- <v-btn icon @click="drawer = !drawer">
          <v-icon>mdi-dots-vertical</v-icon>
        </v-btn> -->
      </div>
    </v-app-bar>

    <v-navigation-drawer v-model="drawer" absolute temporary>
      <LeftMenu />
    </v-navigation-drawer>
  </div>
</template>

<script>
// import SignoutBtn from "./SignoutBtn";
import LeftMenu from "./LeftMenu";

export default {
  data: () => ({
    drawer: false,
    group: null,
  }),

  components: {
    LeftMenu,
    // SignoutBtn,
  },

  watch: {
    group() {
      this.drawer = false;
    },
  },

  computed: {
    isAuthenticated() {
      return this.$store.state.isAuthenticated;
    },
  },

  methods: {
    logout() {
      this.$store.dispatch("userSignOut");
    },
  },
};
</script>
