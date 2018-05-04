$.register_module({
	name: 'og.views.convention_forms.Attributes',
	dependencies: ['og.common.util.ui.Form'],
    obj: function () {
        var module = this, Block = og.common.util.ui.Block, add_table = '.og-add-table',
            template = Handlebars.compile('<tr><td>{{{key}}}</td>\
                    <td>{{{value}}}</td>\
                    <td><a class="OG-link-remove og-js-rem-attribute" href="#">remove</a></td></tr>');
        var Attributes = function (config) {
            var block = this, id = og.common.id('attributes'), form = config.form;
                attr_data = config.attributes ? Object.keys(config.attributes).reduce(function (acc, val) {
                    return acc.concat({key: config.attributes[val]['Key'], value: config.attributes[val]['Value']});
                }, []) : {};
            form.Block.call(block, {
                module: 'og.views.forms.convention-attributes_tash',
                extras: {id: id, data: attr_data},
                processor: function (data) {
                    var attributes = {}, 
                    path = config.index.split('.'), 
                    last = path.pop();
                    $('.og-attributes-add-list li').each(function (i, e) {
                        attributes[$(e).find('.og-attributes-key').val().trim()] = $(e).find('.og-attributes-value').val().trim();
                    });
                    path.reduce(function (acc, val) { return acc[val]; }, data)[last] = attributes;
                }
            });
            block.on('click', '#' + id + ' ' + add_table + ' .og-js-rem-attribute', function (event) {
                $(event.target).closest(".og-js-row").remove();
            }).on('click', '#' + id + ' .og-js-add-attribute', function (event) {
                event.preventDefault();
                var row = $(event.target).closest(".og-js-row"),
                    key = row.find('.attr_key').val(),
                    value = row.find('.attr_val').val();
                if (!key || !value) {
                    return;
                }
                $(add_table).prepend(template({key: key, value: value}));
                row.find('[class^=attr_]').val('');
                $('.attr_key').focus();
            });
        };
        Attributes.prototype = new Block(null, { module: 'og.views.forms.attributes_tash' }); // inherit Block prototype
        return Attributes;
    }
});