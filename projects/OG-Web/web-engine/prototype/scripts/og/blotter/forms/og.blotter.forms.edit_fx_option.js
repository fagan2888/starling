/**
 * Copyright 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * Please see distribution for license.
 */
$.register_module({
    name: 'og.blotter.forms.Edit_fx_option',
    dependencies: [],
    obj: function () {   
        return function () {
            var constructor = this;
            constructor.load = function () {
                var config = {}, dialog; 
                config.title = 'FX Option Termination';
                var form = new og.common.util.ui.Form({
                    module: 'og.blotter.forms.edit_fx_option_tash',
                    data: {},
                    type_map: {},
                    selector: '.OG-blotter-form-block',
                    extras:{}
                });
                form.children.push(
                     new form.Block({
                        module: 'og.blotter.forms.blocks.edit_partial_tash',
                        extras: {}
                    })
                );
                form.dom();
                $('.OG-blotter-form-title').html(config.title);
            }; 
            constructor.load();
            constructor.kill = function () {
            };
        };
    }
});