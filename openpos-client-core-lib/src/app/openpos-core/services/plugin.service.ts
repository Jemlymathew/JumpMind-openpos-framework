import { CordovaDevicePlugin } from './../common/cordova-device-plugin';
import { CordovaPlugin } from './../common/cordova-plugin';
import { IDevicePlugin } from './../common/idevice-plugin';
import { IPlugin } from './../common/iplugin';
import { Injectable } from '@angular/core';

declare var cordova: any;

@Injectable()
export class PluginService {

    private plugins = new Map<string, IPlugin>();

    constructor() {
        document.addEventListener('deviceready', () => {
            console.log('cordova devices are ready for the plugin service');
            // cordova file plugin doesn't put itself in cordova.plugins, so add it there if present.
            // Makes it possible for us to access plugins dynamically by name.
            // There apparently is not a consistent way to access references to
            // cordova plugins.
            if (cordova.file) {
                if (! cordova.plugins || ! cordova.plugins['file']) {
                    cordova.plugins['file'] = cordova.file;
                    console.log('PluginService added cordova-plugin-file to cordova.plugins');
                }
            }
          },
          false);
    }

    public addPlugin(pluginId: string, plugin: IPlugin) {
        this.plugins[pluginId] = plugin;
        console.log(`plugin '${pluginId}' added to the PluginService`);
    }

    public getPlugin(pluginId): Promise<IPlugin> {
        return new Promise( (resolve, reject) => {
            let plugin: IPlugin = this.plugins[pluginId];
            if (! plugin) {
                if (this.isCordovaPlugin(pluginId)) {
                    let cdvPlugin = null;
                    if (cordova.plugins && cordova.plugins[pluginId]
                        && typeof cordova.plugins[pluginId].processRequest !== 'undefined') {
                        cdvPlugin = new CordovaDevicePlugin(pluginId);
                    } else {
                        cdvPlugin = new CordovaPlugin(pluginId);
                    }

                    console.log(`Initializing plugin '${pluginId}'...`);
                    this.pluginInit(cdvPlugin).then(
                      (inittedPlugin) => {
                        this.plugins[pluginId] = inittedPlugin;
                        plugin = inittedPlugin;
                        console.log(`plugin '${pluginId}' initialized`);
                        resolve(inittedPlugin);
                      }
                    ).catch(
                        (error) => {
                            if (error) {
                                reject(error);
                                console.log(error);
                            } else {
                                const err = `plugin '${pluginId}' failed to initialize`;
                                console.log(err);
                                reject(err);
                            }
                        }
                    );
                } else {
                    const msg = `plugin '${pluginId}' not found`;
                    reject(msg);
                    // handle future plugins here
                }
            } else {
                resolve(plugin);
            }
        });
    }

    public getDevicePlugin(pluginId: string): Promise<IDevicePlugin> {
        return new Promise<IDevicePlugin>( (resolve, reject) => {
                const pluginPromise: Promise<IPlugin> = this.getPlugin(pluginId);
                pluginPromise.then(thePlugin => {
                    if (thePlugin && (<IDevicePlugin>thePlugin).processRequest) {
                        resolve(<IDevicePlugin> thePlugin);
                    } else {
                        resolve(null);
                    }
                }).catch(
                    (error) => { reject(error); }
                );
            }
        );
    }

    private pluginInit(plugin: IPlugin): Promise<IPlugin> {
        const returnPromise: Promise<IPlugin> = new Promise(
          (resolve, reject) => {
            plugin.init(
                () => {
                    resolve(plugin);
                },
                (error) => {
                    reject(error);
                }
            );
          }
        );

        return returnPromise;
    }
    /*
     * Assumes plugin is in cordova.plugins data structure
     * See plugin.xml for the plugin name at /plugin/platform/js-module/clobbers[@target]
     * @target should be set to location where the plugin can be accessed,
     * which needs to be cordova.plugins.xyz (where xyz is the pluginId) in order
     * for the plugin to be found.
     */
    public isCordovaPlugin(pluginId: string): boolean {
        return typeof cordova !== 'undefined'
            && typeof cordova.plugins !== 'undefined'
            && (cordova.plugins[pluginId]);
    }

}
