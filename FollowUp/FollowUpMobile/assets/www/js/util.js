var Util = {
    createJsLinkListItem: function (jsCode, linkContent, dataTheme) {
        var url = "javascript:" + jsCode + ";return false;";
        return Util.createLinkListItem(url, linkContent, dataTheme);
    },

    createTelLinkListItem: function (phNum, linkContent, dataTheme) {
        var url = "tel:" + phNum;
        return Util.createLinkListItem(url, linkContent, dataTheme);
    },

    createGeoLinkListItem: function (address, linkContent, dataTheme) {
        var addressString = encodeURI(address.address1 + ", " + address.address2 + ", " + address.city + ", " + address.state + " " + address.zip);
        var url = "geo:0,0?q=" + addressString;
        return Util.createLinkListItem(url, linkContent, dataTheme);
    },

    createLinkListItem: function (url, linkContent, dataTheme) {
        var content = "<a href='" + url + "'>" + linkContent + "</a>";
        return Util.createListItem(content, dataTheme);
    },

    createListItem: function (content, dataTheme) {
        return "<li data-theme='" + dataTheme + "'>" + content + "</li>";
    }

}