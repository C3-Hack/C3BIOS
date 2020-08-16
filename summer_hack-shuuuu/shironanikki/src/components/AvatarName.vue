<template>
  <v-list-item two-line :class="miniVariant && 'px-0'">
    <v-list-item-avatar>
      <img src="https://randomuser.me/api/portraits/men/81.jpg" />
    </v-list-item-avatar>

    <v-list-item-content>
      <v-list-item-title>{{this.usernameDB}}</v-list-item-title>
      <v-list-item-subtitle>@{{this.useridDB}}</v-list-item-subtitle>
    </v-list-item-content>
  </v-list-item>
</template>
<script>
export default {
	name: "AvatarName",

	computed: {
    // isAuthenticated() {
    //   return this.$store.getters.isAuthenticated;
    // },
    usernameDB() {
			return this.$store.state.username;
		},
		useridDB() {
			return this.$store.state.userid;
		},
  },

  mounted() {
    this.getUserInfo();
  },

  methods: {
    getUserInfo() {
      this.$store.dispatch("getUserInfo");
    },
  },
}
</script>>