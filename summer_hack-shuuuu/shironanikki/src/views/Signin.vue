<template>
  <v-flex class="mx-auto mt-5" xs12 sm8 md4>
    <div class="signin">
      <v-card>
        <v-card-title>
          <h1 class="display-1">Sign in</h1>
        </v-card-title>
        <v-card-text>
          <v-form ref="form" v-model="valid" lazy-validation>
            <v-text-field
              type="text"
              name="email"
              label="Email"
              prepend-icon="mdi-email"
              v-model="email"
              :rules="emailRules"
              data-cy="signinEmailField"
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
              data-cy="signinPasswordField"
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
            color="primary"
            @click="submit"
            :disabled="!valid"
            data-cy="signinSubmitBtn"
          >Sign In !</v-btn>
        </v-card-actions>
      </v-card>
    </div>
  </v-flex>
</template>

<script>
export default {
  name: "Signin",
  data() {
    return {
      valid: false,
      email: "",
      password: "",
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
  methods: {
    submit() {
      if (this.$refs.form.validate()) {
        this.$store.dispatch("userLogin", {
          email: this.email,
          password: this.password,
        });
      }
    },
  },
};
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
</style>