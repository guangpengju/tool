<template>
    <el-dialog title="页面预览" :visible.sync="$store.state.showView">
        <comment :is="testData.tage" v-bind="testData.attr.props" v-on="testData.attr.event">{{testData.attr.innerHtml}}</comment>
    </el-dialog>
</template>
<script>
    export default {
        name: 'ViewLayout',
        componentName: 'ViewLayout',
        data() {
            return {
                testData:{
                    "id": "el-button",
                    "label": "按钮",
                    "icon": "el-icon-full-screen",
                    "type": "normal",
                    "tage": "el-button",
                    "className": "",
                    "attr": {
                        "style": {
                            "width": 100,
                            "height": 100
                        },
                        "props": {
                            "size": 'small',
                            "small": 'info',
                            "round": true
                        },
                        "event": {
                            click() {
                                console.log("预览页面click")
                            }
                        },
                        "innerHtml":'测试按钮'
                    },
                    "showHtml": "",
                    "elements": []
                }
            }
        },
        methods: {
        }
    };
</script>

<style scoped>

</style>





















