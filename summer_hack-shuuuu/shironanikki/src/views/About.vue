<template>
  <div class="about">
    <v-row>
      <v-col cols="0" sm="3">
        <LeftMenu />
      </v-col>
      <v-col cols="12" sm="7">
        <h1>This is an about page</h1>
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
  name: "About",
  components: {
    LeftMenu,
    RightMenu,
  },
};
</script>