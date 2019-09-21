<template>
    <div id="mainid" :class="classInfo" @click.stop="choosed">
        <container-factory :layoutDatas="layoutData" v-if="layoutData.type == 'container'" style="z-index: inherit"/>
        <!--<container-factory :element="element" v-if="element.type == 'nomoral'"/>-->
    </div>
</template>

<script>
    export default {
        name: 'MainFactory',
        componentName: 'MainFactory',
        components: {
            ContainerFactory: () => import("@/components/factory/ContainerFactory")
        },
        data(){
            return {
                id:"",
                classInfo:{
                    'full-screen': true,
                    'element-choose': false
                },
                styleMap:{
                    width:{
                        name: "width",
                        suffix: "px"
                    },
                    height:{
                        name: "height",
                        suffix: "px"
                    },
                    flex:{
                        name: "flex-direction"
                    },
                    justify:{
                        name: "justify-content"
                    },
                    align:{
                        name: "align-items"
                    }
                }
            }
        },
        props:{
            layoutData: Object
        },
        watch:{
            '$store.state.chooseData.uuid': function (newVal){
                this.classInfo['element-choose'] = newVal == this.id;
            }
        },
        methods: {
            createStyle(val) {
                if (!val || !val.attr || !val.attr.style) return "";

                let styleStr = "";
                for (let key in val.attr.style) {
                    let styleInfo = this.styleMap[key];
                    if (styleInfo) {
                        styleStr += (styleInfo.name + ": " + val.attr.style[key] + (styleInfo.suffix ? styleInfo.suffix : "") + ";")
                    } else {
                        styleStr += val.attr.style[key];
                    }
                }
                return styleStr;
            },
            createProps() {
                return ""
            },
            createEvent() {
                return ""
            },
            uuid() {
                let uuid = ""
                for (let i = 0; i < 8; i++) {
                    uuid += (((1 + Math.random()) * 0x10000) | 0).toString(16).substring(1);
                }
                return uuid;
            },
            choosed(){
                this.$store.dispatch('setChooseDataUUID', this.id);
                this.$store.dispatch('setChooseDataValue', this.layoutData);
                this.$emit('choosed');
            },
            zIndex(){
                return {

                }
            }
        },
        created() {
            this.id = this.uuid();
        }
    };
</script>

<style scoped>
    .element-choose {
        border: 1px solid #fc4b15;
    }
</style>



















