<template>
    <div class="setuser">
      <v-row>
        <v-col cols="0" sm="3">
          <LeftMenu />
        </v-col>
        <v-col cols="12" sm="7">
          <v-card width="100%">
            <v-card-title>
              <h1 class="display-1">Set User Info</h1>
            </v-card-title>
            <v-card-text>
              <v-form ref="form" v-model="valid" lazy-validation>
                <v-text-field
                  type="text"
                  name="username"
                  label="Username"
                  prepend-icon="mdi-account-circle"
                  v-model="username"
                  :rules="usernameRules"
                  data-cy="setUsernameField"
                  required
                />
                <v-text-field
                  type="text"
                  name="userid"
                  label="UserID"
                  prepend-icon="mdi-at"
                  v-model="userid"
                  :rules="useridRules"
                  data-cy="setUseridField"
                  required
                />

                <v-text-field
                  type="text"
                  name="profiletext"
                  label="Profile Text"
                  prepend-icon="mdi-memo"
                  v-model="profiletext"
                  :rules="profileRules"
                  data-cy="setUseridField"
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
      username: this.usernameDB,
      userid: this.useridDB,
      profiletext: this.profiletextDB,
      //   email: "",
      //   password: "",
      usernameRules: [(v) => !!v || "Username is required"],
      useridRules: [(v) => !!v || "UserID is required"],
      //   emailRules: [
      //     (v) => !!v || "E-mail is required",
      //     (v) => /.+@.+/.test(v) || "E-mail must be valid",
      //   ],
      //   passwordRules: [
      //     (v) => !!v || "Password is required",
      //     (v) => v.length >= 6 || "Password must be greater than 6 characters",
      //   ],
    };
  },

  computed: {
    isAuthenticated() {
      return this.$store.getters.isAuthenticated;
    },
    usernameDB() {
      return this.$store.state.username;
    },
    useridDB() {
      return this.$store.state.userid;
    },
    profiletextDB() {
      return this.$store.state.profiletext;
    },
  },

  mounted() {
    this.getUserInfo();
  },

  methods: {
    submit() {
      if (this.$refs.form.validate()) {
        if (this.isAuthenticated) {
          this.$store.dispatch("setUserInfo", {
            username: this.username,
            userid: this.userid,
            profiletext: this.profiletext,
          });
        }
      }
    },

    getUserInfo() {
      this.$store.dispatch("getUserInfo");
    },
  },
};
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
</style>