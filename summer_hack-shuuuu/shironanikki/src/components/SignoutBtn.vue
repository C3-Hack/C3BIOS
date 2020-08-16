<template>
  <v-btn outline color="gray" @click="logout" data-cy="logout">Logout</v-btn>
</template>

<script>
export default {
	data: () => ({
    //
	}),
	methods: {
    logout() {
      this.$store.dispatch("userSignOut");
    },
  },
};
</script>

<style>
</style>