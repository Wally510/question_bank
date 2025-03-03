<template>
	<view>
		<web-view :src="allUrl"></web-view>
	</view>
</template>
 
<script>
	export default {
		data() {
			return {
				allUrl:'',
				viewerUrl:'/static/pdf/web/viewer.html',//刚解压的文件地址，用来渲染PDF的html
			}
		},
		methods: {
			
		},
		onLoad(option) {
			this.allUrl = this.viewerUrl + '?file=' + option.id//将插件地址与接收的文件地址拼接起来
		}
	}
</script>
 
<style>
 
</style>