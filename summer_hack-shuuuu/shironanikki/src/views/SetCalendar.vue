<template>
  <v-flex class="mx-auto mt-5" xs12 sm8 md4>
    <div class="setcalendar">
      <v-card>
        <v-card-title>
          <h1 class="display-1">Set Calendar</h1>
        </v-card-title>

        <v-card-text>
          <v-form ref="form" v-model="valid" lazy-validation>

            <v-date-picker v-model="date"></v-date-picker>
            <v-list-item
              v-for="(item, idx) in contentsDB"
              :key="idx"
              :color="item.color"
              :to="link"
            >
              <v-list-item-content>
                <v-btn
                block
                dark
                outlined
                rounded
                :color="item.color"
                @click="clickbtn(item.color)"
                >
                  {{ item.contentname }}
                </v-btn>
              </v-list-item-content>
            </v-list-item>
          </v-form>
        </v-card-text>
        <v-divider></v-divider>
        <v-card-actions>
          <v-spacer></v-spacer>
          <v-btn color="success" @click="submit" :disabled="!valid" data-cy="setSubmitBtn">Register!</v-btn>
        </v-card-actions>
      </v-card>
    </div>
  </v-flex>
</template>

<script>
export default {
  name: "setCalendar",
  data() {
    return {
      valid: false,
      date: "",
      color: "",
    };
  },
  computed: {
    isAuthenticated() {
      return this.$store.getters.isAuthenticated;
    },
    contentsDB() {
      return this.$store.state.contents;
    },
  },
  mounted() {//このページが読み込まれるときに実行される
    this.getContents();
  },
  methods: {
    submit() {
      if (this.$refs.form.validate()) {
        if (this.isAuthenticated) {
          this.$store.dispatch("setCalendar", {
            date: this.date,
            color: this.color,
          });
        }
      }
    },
    getContents() {
      this.$store.dispatch("getContents");
    },
    clickbtn(color){
      this.color = color;
    },
  },
};
</script>

<style scoped>
</style>
