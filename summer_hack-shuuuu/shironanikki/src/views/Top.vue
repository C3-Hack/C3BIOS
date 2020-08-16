<template>
	<div class="top">
		<div class="logo">
		<img src="../components/467-logo01.png">
	</div>
	<h1 class="sub">ようこそ467nikkiへ</h1>
	<div v-show="!isAuthenticated" class="btn">
		<v-btn color="primary" to="/signin" data-cy="signinBtn">SIGN IN</v-btn>
		<v-btn color="success" to="/signup" class="nav-join ml-15" data-cy="joinBtn" >JOIN</v-btn>
	</div>
  </div>
</template>
<script>
export default {

};
</script>
<style>
.sub{
	text-align: center;
	padding-top: 20px;
}
	.logo{
		text-align: center;
		padding-top: 10px;
	}
	.btn{
		text-align: center;
		padding-top: 10px;
	}
</style>
