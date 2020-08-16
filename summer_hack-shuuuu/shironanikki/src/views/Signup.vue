<template>
  <v-flex class="mx-auto mt-5" xs12 sm8 md4>
    <div class="signup">
      <v-card>
        <v-card-title>
          <h1 class="display-1">Sign up</h1>
        </v-card-title>
        <v-card-text>
          <v-form ref="form" v-model="valid" lazy-validation>
            <!-- <v-text-field
              type="text"
              name="username"
              label="Username"
              prepend-icon="mdi-account-circle"
              v-model="username"
              :rules="usernameRules"
              data-cy="signupUsernameField"
              required
            />
            <v-text-field
              type="text"
              name="userid"
              label="UserID"
              prepend-icon="mdi-at"
              v-model="userid"
              :rules="useridRules"
              data-cy="signupUseridField"
              required
            /> -->

            <v-text-field
              type="text"
              name="email"
              label="Email"
              prepend-icon="mdi-email"
              v-model="email"
              :rules="emailRules"
              data-cy="signupEmailField"
              required
            />
            <v-text-field
              type="password"
              name="password"
              label="Password"
              prepend-icon="mdi-lock"
              required
              v-model="password"
              :rules="passwordRules"
              data-cy="signupPasswordField"
            />
            <!-- <button @click="signUp">Register</button>
          <p>
            Do you have an account?
            <router-link to="/signin">sign in now!!</router-link>
            </p>-->
          </v-form>
        </v-card-text>
        <v-divider></v-divider>
        <v-card-actions>
          <v-spacer></v-spacer>
          <v-btn
            color="success"
            @click="submit"
            :disabled="!valid"
            data-cy="signupSubmitBtn"
          >Register!</v-btn>
        </v-card-actions>
      </v-card>
    </div>
  </v-flex>
</template>

<script>
// import firebase from "firebase";

export default {
  name: "Signup",
  data() {
    return {
      valid: false,
      // username: "",
      // userid: "",
      email: "",
      password: "",
      // usernameRules: [(v) => !!v || "Username is required"],
      // useridRules: [(v) => !!v || "UserID is required"],
      emailRules: [
        (v) => !!v || "E-mail is required",
        (v) => /.+@.+/.test(v) || "E-mail must be valid",
      ],
      passwordRules: [
        (v) => !!v || "Password is required",
        (v) => v.length >= 6 || "Password must be greater than 6 characters",
      ],
    };
  },

  // computed: {
  //   isAuthenticated() {
  //     return this.$store.getters.isAuthenticated;
  //   },
  // },

  methods: {
    submit() {
      if (this.$refs.form.validate()) {
        this.$store.dispatch("userJoin", {
          // username: this.username,
          // userid: this.userid,
          email: this.email,
          password: this.password,
        });
      }
      // if (this.$refs.form.validate()) {
      //   if (this.isAuthenticated) {
      //     this.$store.dispatch("addUserInfo", {
      //       username: this.username,
      //       userid: this.userid,
      //     });
      //   }
      // }
    },
  },
};
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
</style>