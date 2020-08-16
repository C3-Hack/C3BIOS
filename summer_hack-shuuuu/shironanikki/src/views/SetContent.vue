<template>
  <div class="setcontent">
    <v-row>
      <v-col cols="0" sm="3">
        <LeftMenu />
      </v-col>
      <v-col cols="12" sm="7">
        <v-card width="100%">
          <v-card-title>
            <h1 class="display-1">Set Content</h1>
          </v-card-title>
          <v-card-text>
            <v-form ref="form" v-model="valid" lazy-validation>
              <v-text-field
                type="text"
                name="contentname"
                label="Content Name"
                prepend-icon="mdi-book"
                v-model="contentname"
                :rules="contentnameRules"
                data-cy="setContentameField"
                required
              />

              <v-text-field
                type="text"
                name="color"
                label="Color"
                prepend-icon="mdi-pen"
                v-model="color"
                :rules="colorRules"
                data-cy="setColorField"
                required
              />

              <v-color-picker v-model="color"></v-color-picker>

              <v-text-field
                type="text"
                name="goaltext"
                label="Goal Text"
                prepend-icon="mdi-flag"
                v-model="goaltext"
                :rules="goaltextRules"
                data-cy="setGoalField"
                required
              />
            </v-form>
          </v-card-text>
          <v-divider></v-divider>
          <v-card-actions>
            <v-spacer></v-spacer>
            <v-btn
              color="success"
              @click="submit"
              :disabled="!valid"
              data-cy="setSubmitBtn"
            >Register!</v-btn>
          </v-card-actions>
        </v-card>
      </v-col>
      <v-col cols="0" sm="2">
        <RightMenu />
      </v-col>
    </v-row>
  </div>
</template>

<script>
import LeftMenu from "../components/LeftMenu";
import RightMenu from "../components/RightMenu";

export default {
  name: "SetUser",
  components: {
    LeftMenu,
    RightMenu,
  },
  data() {
    return {
      valid: false,
      contentname: "",
      color: "",
      goaltext: "",

      contentnameRules: [(v) => !!v || "Content Name is required"],
      goaltextRules: [(v) => !!v || "Goal Text is required"],
    };
  },

  computed: {
    isAuthenticated() {
      return this.$store.getters.isAuthenticated;
    },
    // usernameDB() {
    // 		return this.$store.state.username;
    // },
    // useridDB() {
    // 		return this.$store.state.userid;
    // },
    // profiletextDB() {
    // 		return this.$store.state.profiletext;
    // },
  },

  //   mounted() {
  //     this.getUserInfo();
  //   },

  methods: {
    submit() {
      if (this.$refs.form.validate()) {
        if (this.isAuthenticated) {
          this.$store.dispatch("setContent", {
            contentname: this.contentname,
            color: this.color,
            goaltext: this.goaltext,
          });
        }
      }
    },

    // getUserInfo() {
    //   this.$store.dispatch("getContent");
    // },
  },
};
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
</style>
