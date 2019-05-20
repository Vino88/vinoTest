const dkppDataTable = (tableElement, url, options, order) => {
    const defaultOptions = {
        "serverSide": true,
        "searching": (options.searching == false)? false : true,
        "autoWidth": false,
        "ajax": {
            "url": url,
            "data": function(aoData) {
                return {
                    page: (aoData.start / aoData.length),
                    size: aoData.length,
                    sort: order || `${aoData.columns[aoData.order[0].column].data},${aoData.order[0].dir}`,
                    searchParam: aoData.search["value"]
                }
            },
            "dataFilter": function(data) {
                data = JSON.parse(data);
                var json = {
                    "recordsTotal": data.totalElements,
                    "recordsFiltered": data.totalElements,
                    "data": data.content
                };

                return JSON.stringify( json ); // return JSON string
            }
        },
    };

    $.extend(options, defaultOptions);

    return tableElement.DataTable(options);
};

const formToJSON = (elements) => {
    return [].reduce.call(elements, function (data, element) {

        // Make sure the element has the required properties and should be added.
        if (isValidElement(element) && isValidValue(element)) {
            if (isCheckbox(element)) {
                data[element.name] = data[element.name] = (data[element.name] || []).concat(element.value);
            } else if (isMultiSelect(element)) {
                data[element.name] = getSelectValues(element);
            } else {
                if ($(element).data('mask') && $(element).cleanVal) {
                    data[element.name] = $(element).cleanVal();
                } else {
                    data[element.name] = $(element).val();
                }
            }
        }

        return data;
    }, {});
};

const isValidElement = (element) => {
    return element.name && element.value;
};

const isValidValue = (element) => {
    return !['checkbox', 'radio'].includes(element.type) || element.checked;
};

const isCheckbox = (element) => {
    return element.type === 'checkbox';
};

const getSelectValues = (options) => {
    return [].reduce.call(options, function (values, option) {
        return option.selected ? values.concat(option.value) : values;
    }, [])
};

const isMultiSelect = (element) => {
    return element.options && element.multiple;
};

const objectToText = (obj, template) => {
    const pattern = /\$\{(.*?)\}/;
    let value = template;

    while (pattern.test(value)) {
        const fieldMatch = value.match(pattern);
        const replaceValue = obj[fieldMatch[1]] || "";
        value = value.replace(fieldMatch[0], replaceValue);
    }

    return value;
};

const setSelectValue = (elmt, obj, valueField, template) => {
    if (obj === null) return;
    const newOption = new Option(objectToText(obj, template), obj[valueField], false, true);
    elmt.find("option").remove();
    elmt.append(newOption).trigger('change');

    // manually trigger the `select2:select` event
    elmt.trigger({
        type: 'select2:select',
        params: {
            data: obj
        }
    });
};

const fillDateField = function (strDate){
    var myDate = new Date(strDate);
    var myDateString;

    myDateString = ('0' + myDate.getDate()).slice(-2) + '/'
        + ('0' + (myDate.getMonth()+1)).slice(-2) + '/'
        + myDate.getFullYear();
    return myDateString;
};

const mmDdYyyyFormat = function (strDate){
    var myDate = new Date(strDate);
    var myDateString;

    myDateString = ('0' + (myDate.getMonth()+1)).slice(-2) + '/'
        + ('0' + myDate.getDate()).slice(-2) + '/'
        + myDate.getFullYear();
    return myDateString;
};

const ddMmYyyyFormat = function (strDate){
    var myDate = new Date(strDate);
    var myDateString;

    myDateString =  ('0' + myDate.getDate()).slice(-2) + '/'
            + ('0' + (myDate.getMonth()+1)).slice(-2) + '/'
            + myDate.getFullYear();
    return myDateString;
};

const mmDdYyyyToJavaDateFormat = function (strDate){
    var myDate = new Date(strDate);
    var myDateString;

    myDateString = myDate.getFullYear() + '-' + ('0' + (myDate.getMonth()+1)).slice(-2) + '-'
        + ('0' + myDate.getDate()).slice(-2);
    return myDateString;
};

const ddMmYyyyToJavaDateFormat = function (strDate){
    let arrDate = strDate.split("/");
    myDateString = arrDate[2] + "-" + arrDate[1] + "-" + arrDate[0];
    return myDateString;
};

const indonesiaDateToJavaDateFormat = function (strDate){
    let arrDate = strDate.split("-");
    myDateString = arrDate[2] + "-" + arrDate[1] + "-" + arrDate[0];
    return myDateString;
};

const javaDateToIndonesiaDate = function (strDate){
    let arrDate = strDate.split("-");
    myDateString = arrDate[2] + "-" + arrDate[1] + "-" + arrDate[0];
    return myDateString;
};

const genderText = (data) => {
    return data && (data === "P" ? "Pria" : "Wanita");
};

const urlPath = function(){
    let urlPath = window.location.hostname;
    let urlPort = window.location.port;
    let url = 'http://'+urlPath + ':' +urlPort;
    return url;
}

const loader = (parent, display) => {
    let loaderCount = parent.attr("loader-count") || 0;

    if (loaderCount > 0) {
        parent.attr("loader-count", display ? ++loaderCount : --loaderCount);
    } else {
        if (display) {
            const loaderElement = `
                <div class="dkpp-loader">
                    <div>
                        <div class="loader text-center">
                            <div class="loader-inner line-scale">
                                <div>
                                    </div><div>
                                    </div><div>
                                    </div><div>
                                    </div><div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            `;
            parent.append(loaderElement);
            parent.attr("loader-count", ++loaderCount);
        }
    }

    if (loaderCount === 0) {
        parent.removeAttr("loader-count");
        parent.find(".dkpp-loader").remove();
    }
};