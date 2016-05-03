var ajaxUrl = 'ajax/profile/meals/';
var datatableApi;

function updateTable() {
    $.ajax({
        type: "POST",
        url: ajaxUrl + 'filter',
        data: $('#filter').serialize(),
        success: function (data) {
            updateTableByData(data);
        }
    });
    return false;
}

$(function () {
    datatableApi = $('#mealsDatatable').DataTable({
        "ajax": {
            "url": ajaxUrl,
            "dataSrc": ""
        },
        "paging": false,
        "info": true,
        "columns": [
            {
                "data": "dateTime",
            },
            {
                "data": "description",
            },
            {
                "data": "calories",
            },
            {
                "defaultContent": "Edit",
                "orderable": false,
                "render": renderEditBtn
            },
            {
                "defaultContent": "Delete",
                "orderable": false,
                "render": renderDeleteBtn
            }
        ],
        "createdRow": function( row, data, dataIndex ) {
            if ( data.exceed == true ) {
                $(row).addClass( 'exceeded' );
            }
        },
        "order": [
            [
                0,
                "desc"
            ]
        ]
    });

    $('#filter').submit(function () {
        updateTable();
        return false;
    });
    makeEditable();
});