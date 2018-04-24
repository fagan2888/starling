$.register_module({
	name: 'og.views.convention_forms.Attributes',
	dependencies: ['og.common.util.ui.Form'],
    obj: function () {
        var module = this, Block = og.common.util.ui.Block, add_list = '.og-attributes-add-list',
            template = Handlebars.compile('<li><div class="og-del og-js-rem"></div>{{{key}}} = {{{value}}}\
                <input type="hidden" class="og-attributes-key" value="{{{key}}} ">\
                <input type="hidden" class="og-attributes-value" value="{{{value}}}"></li>');
        var Attributes = function (config) {
            var block = this, id = og.common.id('attributes'), form = config.form;
//                attr_data = config.attributes ? Object.keys(config.attributes).reduce(function (acc, val) {
//                    return acc.concat({key: config.attributes[val]['Key'], value: config.attributes[val]['Value']});
//                }, []) : {};
//                console.log(config.attributes);
//                console.log(attr_data);
            form.Block.call(block, {
                module: 'og.views.forms.attributes_tash',
                extras: {id: id, data: config.attributes},
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
            block.on('click', '#' + id + ' ' + add_list + ' .og-js-rem', function (event) {
                $(event.target).parent().remove();
            }).on('click', '#' + id + ' .og-js-add-attribute', function (event) {
                event.preventDefault();
                var $group = $(event.target).parent(), key = $group.find('.attr_key').val(),
                    value = $group.find('.attr_val').val();
                if (!key || !value) {
                    return;
                }
                $(add_list).prepend(template({key: key, value: value}));
                $group.find('[class^=attr_]').val('');
                $('.attr_key').focus();
            });
        };
        Attributes.prototype = new Block(null, { module: 'og.views.forms.attributes_tash' }); // inherit Block prototype
        return Attributes;
    }
});