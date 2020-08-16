<template>
  <v-card
    class="mx-auto"
    width="320"
    max-height="300"
    outlined
  >
    <div class="2020/08">
      <ul>
        <li class="sum">Sun</li>
        <li class="wed">Wed</li>
        <li class="sat">Sat</li>
      </ul>
      <br>
      <div class="month">
        <v-card-text>
          8/1
        </v-card-text>
      </div>
      <v-row
        justify="left"
        align-content="center"
        v-for="n in 4"
        :key="n"
        class="mt-2"
        >
        <v-col v-for="i in 7" :key="i">
          <v-card  outlined tile height=12 width="12"
              >
          </v-card>
        </v-col>
      </v-row>
    </div>
  </v-card>
</template>

<script>
export default {
  name: "2020-08",
  data () {
    return {
      list: [],  // 最新状態はここにコピーされる
    }
  },
  computed: {
    calendarDB() {
      return this.$store.state.calendar;
    },
  },
  mounted() {//このページが読み込まれるときに実行される
    this.getCalendar();
  },
  methods: {
    getCalendar() {
      this.$store.dispatch("getCalendar");
    },
  }
};
</script>
<style>
.aas{
  padding-left: 262px;
  padding-right:5px;
  padding-bottom: 9px;
  padding-top:5px;
}
.month{
  padding-top: 5px;
  padding-top: 0px;
  padding-bottom:100px;
  padding-left: 0px;
  padding-right:0px;
  float: left;
}
li {
  list-style: none;
  float: left;
}
.sum{
  padding-left:33px;
}
.wed{
  padding-left: 83px;
}
.sat{
  padding-left: 83px;
}
</style>
