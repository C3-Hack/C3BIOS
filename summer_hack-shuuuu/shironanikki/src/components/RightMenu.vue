<template>
  <v-list nav class="py-0">
    <v-list-item v-for="(item, idx) in contentsDB" :key="idx">
      <v-btn
        block
        dark
        outlined
        large
        rounded
        :color="item.color"
        @click="clickbtn(item.contentname, item.color)"
      >
        <v-icon>mdi-pen</v-icon>
        {{ item.contentname }}
      </v-btn>
    </v-list-item>

    <v-dialog v-model="dialog" max-width="500px">
      <v-card class="pa-3">
        <AvatarName />
        <v-textarea 
          :label="label" 
          outlined rows="7" 
          :color="color" 
          v-model="text"
        >
        </v-textarea>

        <v-card-actions>
          <v-spacer></v-spacer>
          <v-btn text :color="color" @click="submit">Submit</v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>
  </v-list>
</template>

<script>
import AvatarName from "./AvatarName";
import moment from 'moment'

export default {
  name: "RightMenu",
  data() {
    return {
      contentname: "",
      color: "",
      dialog: false,
      label: "",
      text:"",
    };
  },
  components: {
    AvatarName,
  },
  computed: {
    contentsDB() {
      return this.$store.state.contents;
    },
  },
  mounted() {
    this.getContents();
  },
  methods: {
    getContents() {
      this.$store.dispatch("getContents");
    },

    clickbtn(contentname, color) {
      this.dialog = true;
      this.contentname = contentname;
      this.color = color;
      this.label = "Today's " + this.contentname + " achievement!";
    },

    submit() {
      this.dialog = false;
      this.$store.dispatch("setCard", {
        text: this.text,
        color: this.color,
        date: moment().format('YYYY-MM-DD'),
      });
    },
  },
};
</script>