//yonghuming 4wei mima  6wei
function Encrypt(name, pwd) {
	var replaceStr ="notpassscripteszhdngshao";
	var temppwd = pwd.substring(0, 3) + name + pwd.substring(3, pwd.length);
	pwd = changeStr(temppwd, 4, 7, replaceStr.substring(4, 7));
	//change
	var firstVar=pwd.substring(0, 1);
	var lastVar=pwd.substring(pwd.length - 1, pwd.length);
	pwd = changeStr(pwd, pwd.length - 1, pwd.length,firstVar);
	pwd = changeStr(pwd, 0, 1,lastVar);
	var firstVar=pwd.substring(1, 2);
	var lastVar=pwd.substring(pwd.length - 2, pwd.length-1);
	pwd = changeStr(pwd, pwd.length - 2, pwd.length - 1,firstVar);
	pwd = changeStr(pwd, 1, 2,lastVar);
	return pwd;
}
function changeStr(allstr, start, end, changeStr) {
	var temp= allstr.substring(0, start) + changeStr + allstr.substring(end, allstr.length);
	return temp;
}

