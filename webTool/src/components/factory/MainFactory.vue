<template>
    <div class="full-screen">
        <container-factory :element="element" v-if="element.type == 'container'"/>
        <container-factory :element="element" v-if="element.type == 'nomoral'"/>
    </div>
</template>

<script>
    import ContainerFactory from "@/components/factory/ContainerFactory";
    export default {
        name: 'MainFactory',
        componentName: 'MainFactory',
        components: {
            ContainerFactory
        },
        props:{
            element: Object
        },
        methods:{
            createStyle(val){
                if(!val || !val.attr || !val.attr.style) return "";
                
                let styleStr = "";
                for (let key in val.attr.style) {
                    let styleInfo = this.styleMap[key];
                    if(styleInfo){
                        styleStr += (styleInfo.name + ": " + val.attr.style[key] + (styleInfo.suffix?styleInfo.suffix:"") + ";")
                    }else{
                        styleStr += val.attr.style[key];
                    }
                }
                return styleStr;
            },
            createProps(){
                return ""
            },
            createEvent(){
                return ""
            }
        }
    };
</script>



















