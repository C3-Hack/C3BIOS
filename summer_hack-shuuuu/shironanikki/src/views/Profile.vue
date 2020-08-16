<template>
  <div class="profile">
    <v-row>
      <v-col cols="0" sm="3">
        <LeftMenu />
      </v-col>
      <v-col cols="12" sm="7">
        <!-- start profile area -->
        <!-- <v-card class="pa-2" tile flat color="grey lighten-2">
          <h1>HEADER-IMAGE</h1>
        </v-card> -->
        <v-card class="d-lg-flex" outlined tile flat color="grey lighten-2">
          <v-card class="pa-2 flex-column col-12 col-lg-6" flat tile color="grey lighten-1">
            <AvatarName />
            <v-card-text>{{this.profiletextDB}}</v-card-text>
            <v-card-actions>
              <v-spacer></v-spacer>
              <v-btn icon to="/setuser">
                <v-icon>mdi-pencil</v-icon>
              </v-btn>
            </v-card-actions>
          </v-card>
          <v-card class="pa-2 col-12 col-lg-6" flat tile>
            <Calendar />
          </v-card>
        </v-card>
        <!-- end profile area -->
        <!-- start card area -->
        <v-row>
          <v-col cols="0" sm="1"></v-col>
          <v-col cols="12" sm="10">
            <v-list nav class="py-0">
              <v-list-item v-for="(item, idx) in cardsDB" :key="idx">
                <v-card 
                  width="100%" 
                  class="mx-auto" 
                  outlined 
                  shaped
                  :color="item.color"
                >
                  <AvatarName />
                  <!-- <v-img src="https://cdn.vuetifyjs.com/images/cards/mountain.jpg" width="100%"></v-img> -->

                  <v-card-text>
                    {{item.text}}
                  </v-card-text>

                  <v-card-actions>
                    <v-btn icon>
                      <v-icon>mdi-heart</v-icon>
                    </v-btn>
                    <v-spacer></v-spacer>
                    <v-btn icon>
                      <v-icon>mdi-share-variant</v-icon>
                    </v-btn>
                  </v-card-actions>
                </v-card>
              </v-list-item>
            </v-list>
          </v-col>
          <v-col cols="0" sm="1"></v-col>
        </v-row>
        <!--end card area-->
      </v-col>
      <v-col cols="0" sm="2">
        <RightMenu />
      </v-col>
    </v-row>
  </div>
</template>

<script>
// @ is an alias to /src
// import HelloWorld from '@/components/HelloWorld.vue'
import Calendar from "../components/Calendar";
import LeftMenu from "../components/LeftMenu";
import RightMenu from "../components/RightMenu";
import AvatarName from "../components/AvatarName";

export default {
  name: "Profile",
  components: {
    Calendar,
    LeftMenu,
    AvatarName,
    RightMenu,
  },

  computed: {
    // isAuthenticated() {
    //   return this.$store.getters.isAuthenticated;
    // },
    // usernameDB() {
    // 	return this.$store.state.username;
    // },
    // useridDB() {
    // 	return this.$store.state.userid;
    // },
    profiletextDB() {
      return this.$store.state.profiletext;
    },
    cardsDB() {
      return this.$store.state.cards;
    },
  },

  mounted() {
    this.getUserInfo();
    this.getCards();
  },

  methods: {
    getUserInfo() {
      this.$store.dispatch("getUserInfo");
    },
    getCards() {
      this.$store.dispatch("getCards");
    },
  },
};
</script>