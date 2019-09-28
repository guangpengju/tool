export default {
    editConfig:{
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
    },
    uuid() {
        let uuid = ""
        for (let i = 0; i < 8; i++) {
            uuid += (((1 + Math.random()) * 0x10000) | 0).toString(16).substring(1);
        }
        return uuid;
    },
    deepCopy(obj) {
        var o;
        if (typeof obj === 'object') {
            // 如果  他是空的话
            if (obj === null) {
                o = null;
            } else {
                if (obj instanceof Array) {
                    o = [];
                    for (var i = 0, len = obj.length; i < len; i++) {
                        o.push(this.deepCopy(obj[i]));
                    }
                } else {
                    o = {};
                    for (var j in obj) {
                        o[j] = this.deepCopy(obj[j]);
                    }
                }
            }
        } else {
            o = obj;
        }
        return o;
    },
    createStyle (obj) {
        if (!obj || !obj.attr || !obj.attr.style) return "";

        let styleStr = "";
        for (let key in obj.attr.style) {
            let styleInfo = this.editConfig.styleMap[key];
            if (styleInfo) {
                styleStr += (styleInfo.name + ": " + obj.attr.style[key] + (styleInfo.suffix ? styleInfo.suffix : "") + ";")
            } else {
                styleStr += obj.attr.style[key];
            }
        }
        return styleStr;
    }
}