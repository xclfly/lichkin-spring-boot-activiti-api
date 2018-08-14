function getScrollTop() {
  var scrollTop = 0;
  if (document.documentElement && document.documentElement.scrollTop) {
    scrollTop = document.documentElement.scrollTop;
  } else if (document.body) {
    scrollTop = document.body.scrollTop;
  }
  return scrollTop;
}

function getClientHeight() {
  var clientHeight = 0;
  if (document.body.clientHeight && document.documentElement.clientHeight) {
    var clientHeight = (document.body.clientHeight < document.documentElement.clientHeight) ? document.body.clientHeight : document.documentElement.clientHeight;
  } else {
    var clientHeight = (document.body.clientHeight > document.documentElement.clientHeight) ? document.body.clientHeight : document.documentElement.clientHeight;
  }
  return clientHeight;
}

function getScrollHeight() {
  return Math.max(document.body.scrollHeight, document.documentElement.scrollHeight);
}

function isPageBottom() {
  if (getScrollTop() + getClientHeight() == getScrollHeight()) {
    return true;
  } else {
    return false;
  }
}