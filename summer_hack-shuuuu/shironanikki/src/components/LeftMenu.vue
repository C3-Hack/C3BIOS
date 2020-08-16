<template>
  <v-list dense nav class="py-0">
    <AvaterName />

    <v-divider></v-divider>

    <v-list-item v-for="item in items" :key="item.title" :to="item.link">
      <v-list-item-icon>
        <v-icon>{{ item.icon }}</v-icon>
      </v-list-item-icon>
      <v-list-item-content>
        <v-list-item-title>{{ item.title }}</v-list-item-title>
      </v-list-item-content>
    </v-list-item>
    <v-divider></v-divider>

    <v-list-item>
      <SignoutBtn />
    </v-list-item>

  </v-list>
</template>
<script>
import SignoutBtn from "./SignoutBtn";
import AvaterName from "./AvatarName";

export default {
	data: () => ({
    items: [
      { title: "Home", icon: "mdi-home", link: "/home" },
      { title: "Profile", icon: "mdi-account", link: "/profile" },
      { title: "About", icon: "mdi-help-box", link: "/about" },
      { title: "Test", icon: "mdi-test", link: "/test" },
      { title: "SetUserInfo", icon: "mdi-robot", link: "/setuser" },
      { title: "SetContent", icon: "mdi-robot", link: "/setcontent" },
      { title: "SetCalendar", icon: "mdi-robot", link: "/setcalendar" },
    ],
  }),

  components: {
    SignoutBtn,
    AvaterName,
  },
}
</script>
