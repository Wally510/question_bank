<template>
	<view style="position: absolute; left: 15%; top: 20%; flex-wrap: wrap; display: flex; width: 70%;">
		<view style="border-radius: 15%; background-color: #00aaff; width: 48%; aspect-ratio: 1 / 1;  box-shadow:0 0 10px 1px #cacaca; color: #ffffff; text-align: center; font-size: 150%; line-height: 500%;" @click="gotiku">题库</view>
		<view style="border-radius: 15%; margin-left: 4%; background-color: #55aa00; width: 48%; aspect-ratio: 1 / 1; box-shadow:0 0 10px 1px #cacaca; color: #ffffff; text-align: center; font-size: 150%; line-height: 500%;" @click="gozujuan">组卷</view>
		<view style="border-radius: 15%; margin-top: 4%; background-color: #ffaa00; width: 48%; aspect-ratio: 1 / 1; box-shadow:0 0 10px 1px #cacaca; color: #ffffff; text-align: center; font-size: 150%; line-height: 500%;">答卷情况</view>
		<view style="border-radius: 15%; margin-top: 4%; margin-left: 4%; background-color: #aaff00; width: 48%; aspect-ratio: 1 / 1; box-shadow:0 0 10px 1px #cacaca; color: #ffffff; text-align: center; font-size: 150%; line-height: 500%;"></view>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				
			}
		},
		onLoad() {

		},
		methods: {
			gotiku(){
				console.log("题库");
				uni.navigateTo({
					url:"/pages/paper/query_question"
				});
			},
			gozujuan(){
				console.log("组卷");
				uni.navigateTo({
					url:"/pages/paper/paper"
				});
			}
		}
	}
</script>

<style>
	
</style>
