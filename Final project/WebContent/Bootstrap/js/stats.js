var win = document.getElementById("winId").value;
var mac = document.getElementById("macId").value;
var uni = document.getElementById("unixId").value;
var and = document.getElementById("andId").value;
var iph = document.getElementById("iphoneId").value;
var unk = document.getElementById("unknId").value;

var myDataOS =  [
    {label: 'Windows', value: win },
    {label: 'Mac', value: mac },
    {label: 'Unix', value: uni },
    {label: 'Android', value: and },
    {label: 'Iphone', value: iph },
    {label: 'Unknown', value: unk }
];

var iex = document.getElementById("ieId").value;
var saf = document.getElementById("safId").value;
var ope = document.getElementById("oprId").value;
var chr = document.getElementById("chrId").value;
var net = document.getElementById("netId").value;
var fir = document.getElementById("firId").value;
var unkb = document.getElementById("ubId").value;

var myDataBro =  [
    {label: 'IE', value: iex },
    {label: 'Safari', value: saf },
    {label: 'Opera', value: ope },
    {label: 'Chrome', value: chr },
    {label: 'Netscape', value: net },
    {label: 'Firefox', value: fir },
    {label: 'Unknown', value: unkb }
];

var ses = document.getElementById("sesId").value;
var myData = [{device: 'Users', sells: ses}];

// Morris Bar Chart
Morris.Bar({
    element: 'hero-bar',
    data:  myData,
    xkey: 'device',
    ykeys: ['sells'],
    labels: ['Sells'],
    barRatio: 0.4,
    xLabelMargin: 10,
    hideHover: 'auto',
    barColors: ["#3d88ba"]
});


// Morris Donut Chart
Morris.Donut({
    element: 'hero-donut',
    data: myDataOS,
    colors: ["#30a1ec", "#76bdee", "#c4dafe"],
});

// Morris Donut Chart
Morris.Donut({
    element: 'hero-donut2',
    data: myDataBro,
    colors: ["#30a1ec", "#76bdee", "#c4dafe"],
});
