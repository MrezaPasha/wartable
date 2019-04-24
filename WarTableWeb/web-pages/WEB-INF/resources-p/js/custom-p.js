/****DO-NoT-REMOVE-IT*#- ==============  START  ============== -#*/
var CustomPanelJs = function () {
    var runNotice = function () {
        var nc = $('#n-count-id').html();
        for (i = 0; i < nc; i++) {
            $('#notice-id-' + i).animate({
                top: '+=' + (100 + (60 * nc) - (i * 60)),
                opacity: '1'
            }, 1000 + 1000 * i).animate({opacity: '1'}, 3000).animate({
                opacity: '0.5'
            }, 1000);
        }
        ;
        $('.alert-notice').hover(function () {
            $(this).animate({opacity: '1'});
        });
        $('.alert-notice').mouseleave(function () {
            $(this).animate({opacity: '0.3'});
        });
        $('.alert-notice').click(function () {
            $(this).hide();
        });
    };
    var runRightMenu = function () {
        if ($('#amId').val() !== undefined && $('#amId').val() !== "") {
            var sp = $('#amId').val().split(",");
            for (i = 0; i < sp.length; i++) {
                $('[data-menu-id*="|' + sp[i] + '|"],[data-menu-id="' + sp[i] + '"]').css({"display": "list-item", "visibility": "visible"});
                $('[data-menu-id*="|' + sp[i] + '|"],[data-menu-id="' + sp[i] + '"]').parents("li").css({"display": "block", "visibility": "visible"});
            }
        }
        $('[data-menu-id*="|' + $("#rightMenuIdHandler").val() + '|"],[data-menu-id="' + $("#rightMenuIdHandler").val() + '"]').each(function () {
            $(this).addClass("active");
            $(this).parents("li").addClass("active").addClass("open");
        });
    };
    var runPropertor = function (prop) {
        var saveSuccessAnimate = function () {
            $(".save-success").fadeIn();
            setTimeout(function () {
                $('.save-success').fadeOut();
            }, 1000);
            setTimeout(function () {
                $('.save-success').remove();
            }, 1200);
        }
        var saveFailedAnimate = function () {
            $(".save-failed").fadeIn();
            setTimeout(function () {
                $('.save-failed').fadeOut();
            }, 1000);
            setTimeout(function () {
                $('.save-failed').remove();
            }, 1200);
        }
        var setProp = function (_this) {
            var isOk;
            var formData = new FormData();
            formData.append("id", _this.data("id"));
            formData.append("type", _this.data("type"));
            formData.append("value", (_this.attr("type") === "checkbox") ? _this.is(':checked') : _this.val());
            $.ajax({
                url: $("#cpId").val() + "/panel/propertor/" + prop + "/set",
                Type: 'json',
                type: 'POST',
                data: formData,
                processData: false,
                contentType: false,
                success: function (data) {
                    isOk = data.result.isOk;
                },
                error: function () {
                    isOk = false;
                },
                async: false
            });

            if (isOk) {
                if (_this.attr("type") === "checkbox") {
                    _this.parent().before("<i class='clip-checkmark-2 save-success'></i>");
                    saveSuccessAnimate();
                } else {
                    _this.before("<i class='clip-checkmark-2 save-success'></i>");
                    saveSuccessAnimate();
                }
            } else {
                if (_this.attr("type") === "checkbox") {
                    _this.parent().before("<i class='clip-notification save-failed'></i>");
                    saveFailedAnimate();
                } else {
                    _this.before("<i class='clip-notification save-failed'></i>");
                    saveFailedAnimate();
                }
            }
        };
        $("input[type='checkbox']").change(function () {
            setProp($(this));
        });
        $("input[type='text'],input[type='password'],input[type='number']").change(function () {
            setProp($(this));
        });
        $("textarea").change(function () {
            setProp($(this));
        });
        $("select").change(function () {
            setProp($(this));
        });
        $("[i-propertor-default]").click(function () {
            var name = $(this).attr("i-propertor-name");
            var target = $("[name='" + name + "']");
            if ((target.is(':checkbox') && target.data("toggle") != undefined)) {
                if ((target.prop("checked") && $(this).attr("i-propertor-default") === "true") ||
                    (!target.prop("checked") && $(this).attr("i-propertor-default") === "false")
                ) {
                    return;
                }
                target.val($(this).attr("i-propertor-default"));
                target.bootstrapToggle(target.val() === "true" ? "on" : "off");
            } else {
                if (target.val() == $(this).attr("i-propertor-default")) {
                    return;
                }
                target.val($(this).attr("i-propertor-default"));
                setProp(target);
            }
        });
    };
    var runPwLength = function () {
        var options = {
            onLoad: function () {
                $('label').html('Start typing password');
            }
        };
        options.ui = {
            bootstrap3: true,
            container: "#pwd-container",
            showVerdictsInsideProgressBar: true,
            viewports: {
                progress: ".pwstrength_viewport_progress"
            },
        };
        $('#newPassword').pwstrength(options);
    };
    var runRandomUsername = function () {
        var generate = function () {
            var un;
            $.ajax({
                url: $("#cpId").val() + "/panel/user/username/random",
                Type: 'json',
                type: 'POST',
                success: function (data) {
                    console.log(data);
                    un = data.result.username;
                },
                error: function (err) {
                    alert("خطا در تولید نام کاربری!");
                },
                async: false
            });
            return un;
        }
        $("#randomButton").click(function () {
            var un = generate();
            if (un !== "") {
                $("input[name=username]").val(un);
            }
        })
    };
    var runNoteSync = function () {
        var syncNotes = function () {
            var un;
            $.ajax({
                url: $("#cpId").val() + "/panel/note/sync",
                Type: 'json',
                type: 'POST',
                success: function (data) {
                    // console.log(data);
                    if (data !== undefined && data.status === "ok") {
                        $('#noteSyncModal').modal('show');
                        console.log("ookk");
                        var hx = "<table class='note-sync-table'><thead><tr><th>عنوان</th><th>زمان</th><th>اهمیت</th><th></th></tr></thead><tbody>";
                        $.each(data.result.notes, function (i, v) {
                            console.log(v);
                            hx +=
                                '<tr><td class="note-title">' + v.title + '</td>'
                                + '<td class="note-time">' + v.dateTime + '</td>'
                                + '<td class="note-importance">' + v.importance + '</td>'
                                + '<td class="note-show"><a href="' + $('#cpId').val() + '/panel/note/details/' + v.id + '">مشاهده</a> </td></tr>'
                        });
                        hx += "</tbody></table>"
                        $("#noteSyncTitle").html(data.result.header);
                        $("#noteSyncBody").html(hx);
                    }
                },
                error: function (err) {
                    console.log(err);
                },
                async: false
            });
            return un;
        }
        if ($("#noteMenu").length > 0 && $("#noteMenu").css("visibility") == "visible") {
            syncNotes();
            setInterval(syncNotes, 3000);
        }
    };
    var run3DxLoader = function () {
        debugger;
        var parentContainer = $("[i-three-object]");
        parentContainer.attr("id", "idX1212x")
        var _mode = parentContainer.attr("i-three-mode");
        var _objectSrc = parentContainer.attr("i-three-object");
        var _textureSrc = parentContainer.attr("i-three-texture");
        var _materialSrc = parentContainer.attr("i-three-material");
        var _modelName = parentContainer.attr("i-three-name");
        var isFbx = _modelName.indexOf(".fbx") != -1;

        parentContainer.removeAttr("i-three-object")
            .removeAttr("i-three-texture")
            .removeAttr("i-three-material")
            .removeAttr("i-three-name");
        if (_objectSrc == undefined || _objectSrc === "") {
            return;
        }

        var runObjectLoaderMode1 = function () {
            var container;
            var camera, scene, renderer;
            var mouseX = 0, mouseY = 0;
            var windowHalfX = parentContainer.width() / 2;
            var windowHalfY = parentContainer.height() / 2;
            var object;

            init();
            animate();

            function init() {
                container = document.createElement('div');
                parentContainer.append(container);
                // document.getElementById("objectContainer").appendChild(container);
                camera = new THREE.PerspectiveCamera(45, parentContainer.width() / parentContainer.height(), 1, 2000);
                camera.position.z = 250;
                // scene
                scene = new THREE.Scene();
                var ambientLight = new THREE.AmbientLight(0xcccccc, 0.4);
                scene.add(ambientLight);
                var pointLight = new THREE.PointLight(0xffffff, 0.8);
                camera.add(pointLight);
                scene.add(camera);

                // manager
                function loadModel() {
                    object.traverse(function (child) {
                        if (child.isMesh && _textureSrc != undefined) child.material.map = texture;
                    });
                    object.position.y = -95;
                    scene.add(object);
                }

                var manager = new THREE.LoadingManager(loadModel);
                manager.onProgress = function (item, loaded, total) {
                    console.log(item, loaded, total);
                };
                // texture
                if (_textureSrc != undefined) {
                    var textureLoader = new THREE.TextureLoader(manager);
                    var texture = textureLoader.load(_textureSrc);
                }

                // model
                function onProgress(xhr) {
                    if (xhr.lengthComputable) {
                        var percentComplete = xhr.loaded / xhr.total * 100;
                        console.log('model ' + Math.round(percentComplete, 2) + '% downloaded');
                    }
                }

                function onError() {
                }

                var loader = new THREE.OBJLoader(manager);
                loader.load(_objectSrc, function (obj) {
                    object = obj;
                }, onProgress, onError);
                //
                renderer = new THREE.WebGLRenderer();
                renderer.setPixelRatio(window.devicePixelRatio);
                renderer.setSize(parentContainer.width(), parentContainer.height());
                container.appendChild(renderer.domElement);
                document.addEventListener('mousemove', onDocumentMouseMove, false);
                //
                window.addEventListener('resize', onWindowResize, false);
            }

            function onWindowResize() {
                windowHalfX = parentContainer.width() / 2;
                windowHalfY = parentContainer.height() / 2;
                camera.aspect = parentContainer.width() / parentContainer.height();
                camera.updateProjectionMatrix();
                renderer.setSize(parentContainer.width(), parentContainer.height());
            }

            function onDocumentMouseMove(event) {
                mouseX = (event.clientX - windowHalfX) / 2;
                mouseY = (event.clientY - windowHalfY) / 2;
            }

            function animate() {
                requestAnimationFrame(animate);
                render();
            }

            function render() {
                camera.position.x += (mouseX - camera.position.x) * .05;
                camera.position.y += (-mouseY - camera.position.y) * .05;
                camera.lookAt(scene.position);
                renderer.render(scene, camera);
            }
        };

        var runObjectLoaderMode2 = function () {

            var OBJLoader2Example = function (elementToBindTo) {
                this.renderer = null;
                this.canvas = elementToBindTo;
                this.aspectRatio = 1;
                this.recalcAspectRatio();

                this.scene = null;
                this.cameraDefaults = {
                    posCamera: new THREE.Vector3(0.0, 175.0, 500.0),
                    posCameraTarget: new THREE.Vector3(0, 0, 0),
                    near: 0.1,
                    far: 10000,
                    fov: 45
                };
                this.camera = null;
                this.cameraTarget = this.cameraDefaults.posCameraTarget;

                this.controls = null;
            };

            OBJLoader2Example.prototype = {

                constructor: OBJLoader2Example,

                initGL: function () {
                    this.renderer = new THREE.WebGLRenderer({
                        canvas: this.canvas,
                        antialias: true,
                        autoClear: true
                    });
                    this.renderer.setClearColor(0x050505);

                    this.scene = new THREE.Scene();

                    this.camera = new THREE.PerspectiveCamera(this.cameraDefaults.fov, this.aspectRatio, this.cameraDefaults.near, this.cameraDefaults.far);
                    this.resetCamera();
                    this.controls = new THREE.TrackballControls(this.camera, this.renderer.domElement);

                    var ambientLight = new THREE.AmbientLight(0x404040);
                    var directionalLight1 = new THREE.DirectionalLight(0xC0C090);
                    var directionalLight2 = new THREE.DirectionalLight(0xC0C090);

                    directionalLight1.position.set(-100, -50, 100);
                    directionalLight2.position.set(100, 50, -100);

                    this.scene.add(directionalLight1);
                    this.scene.add(directionalLight2);
                    this.scene.add(ambientLight);

                    var helper = new THREE.GridHelper(1200, 60, 0xFF4444, 0x404040);
                    this.scene.add(helper);
                },

                initContent: function () {
                    this._reportProgress({detail: {text: 'Loading: ' + _modelName}});

                    var scope = this;
                    var objLoader = new THREE.OBJLoader2();
                    var callbackOnLoad = function (event) {
                        scope.scene.add(event.detail.loaderRootNode);
                        console.log('Loading complete: ' + event.detail.modelName);
                        scope._reportProgress({detail: {text: ''}});
                    };

                    var onLoadMtl = function (materials) {
                        objLoader.setModelName(_modelName);
                        if (materials != null) {
                            objLoader.setMaterials(materials);
                        }
                        objLoader.setLogging(true, true);
                        objLoader.load(_objectSrc, callbackOnLoad, null, null, null, false);
                    };

                    if (_materialSrc != undefined) {
                        objLoader.loadMtl(_materialSrc, null, onLoadMtl);
                    } else {
                        onLoadMtl(null);
                    }
                },

                _reportProgress: function (event) {
                    /*var output = THREE.LoaderSupport.Validator.verifyInput(event.detail.text, '');
                    console.log('Progress: ' + output);
                    document.getElementById('feedback').innerHTML = output;*/
                },

                resizeDisplayGL: function () {
                    this.controls.handleResize();

                    this.recalcAspectRatio();
                    this.renderer.setSize(this.canvas.offsetWidth, this.canvas.offsetHeight, false);

                    this.updateCamera();
                },

                recalcAspectRatio: function () {
                    this.aspectRatio = (this.canvas.offsetHeight === 0) ? 1 : this.canvas.offsetWidth / this.canvas.offsetHeight;
                },

                resetCamera: function () {
                    this.camera.position.copy(this.cameraDefaults.posCamera);
                    this.cameraTarget.copy(this.cameraDefaults.posCameraTarget);

                    this.updateCamera();
                },

                updateCamera: function () {
                    this.camera.aspect = this.aspectRatio;
                    this.camera.lookAt(this.cameraTarget);
                    this.camera.updateProjectionMatrix();
                },

                render: function () {
                    if (!this.renderer.autoClear) this.renderer.clear();
                    this.controls.update();
                    this.renderer.render(this.scene, this.camera);
                }

            };

            var _canvas = document.createElement('canvas');
            parentContainer.append(_canvas);
            var app = new OBJLoader2Example(_canvas);

            var resizeWindow = function () {
                app.resizeDisplayGL();
            };

            var render = function () {
                requestAnimationFrame(render);
                app.render();
            };

            window.addEventListener('resize', resizeWindow, false);

            console.log('Starting initialisation phase...');
            app.initGL();
            app.resizeDisplayGL();
            app.initContent();

            render();
        };

        var runFbxLoaderMode = function () {
            if (WEBGL.isWebGLAvailable() === false) {
                document.body.appendChild(WEBGL.getWebGLErrorMessage());
            }

            var stats, controls;
            var camera, scene, renderer, light;

            var clock = new THREE.Clock();

            var mixer;

            init();
            animate();

            function init() {

                camera = new THREE.PerspectiveCamera(45, parentContainer.width() / parentContainer.height(), 1, 2000);
                camera.position.set(100, 200, 300);

                controls = new THREE.OrbitControls(camera, document.getElementById(parentContainer.attr("id")));
                controls.target.set(0, 100, 0);
                controls.update();

                scene = new THREE.Scene();
                scene.background = new THREE.Color(0xa0a0a0);
                scene.fog = new THREE.Fog(0xa0a0a0, 200, 1000);

                light = new THREE.HemisphereLight(0xffffff, 0x444444);
                light.position.set(0, 200, 0);
                scene.add(light);

                light = new THREE.DirectionalLight(0xffffff);
                light.position.set(0, 200, 100);
                light.castShadow = true;
                light.shadow.camera.top = 180;
                light.shadow.camera.bottom = -100;
                light.shadow.camera.left = -120;
                light.shadow.camera.right = 120;
                scene.add(light);

                // scene.add( new THREE.CameraHelper( light.shadow.camera ) );

                // ground
                var mesh = new THREE.Mesh(new THREE.PlaneBufferGeometry(2000, 2000), new THREE.MeshPhongMaterial({color: 0x999999, depthWrite: false}));
                mesh.rotation.x = -Math.PI / 2;
                mesh.receiveShadow = true;
                scene.add(mesh);

                var grid = new THREE.GridHelper(2000, 20, 0x000000, 0x000000);
                grid.material.opacity = 0.2;
                grid.material.transparent = true;
                scene.add(grid);

                // model
                var loader = new THREE.FBXLoader();
                loader.load(_objectSrc, function (object) {

                    mixer = new THREE.AnimationMixer(object);

                    var action = mixer.clipAction(object.animations[0]);
                    action.play();

                    object.traverse(function (child) {

                        if (child.isMesh) {

                            child.castShadow = true;
                            child.receiveShadow = true;

                        }

                    });

                    scene.add(object);

                });

                renderer = new THREE.WebGLRenderer({antialias: true});
                renderer.setPixelRatio(window.devicePixelRatio);
                renderer.setSize(parentContainer.width(), parentContainer.height());
                renderer.shadowMap.enabled = true;

                parentContainer.append(renderer.domElement);

                window.addEventListener('resize', onWindowResize, false);

                // stats
                // stats = new Stats();
                // parentContainer.append(stats.dom);

            }

            function onWindowResize() {

                camera.aspect = parentContainer.width() / parentContainer.height();
                camera.updateProjectionMatrix();

                renderer.setSize(parentContainer.width(), parentContainer.height());

            }

            //

            function animate() {

                requestAnimationFrame(animate);

                var delta = clock.getDelta();

                if (mixer) mixer.update(delta);

                renderer.render(scene, camera);

                // stats.update();

            }

        };

        if (isFbx) {
            runFbxLoaderMode();
        } else {
            switch (_mode) {
                case "1":
                    runObjectLoaderMode1();
                    break;
                case "2":
                    runObjectLoaderMode2();
                    break;
                default:
                    runObjectLoaderMode1();
                    break;
            }
        }

    }


    return {
        init: function () {
            runNotice();
            runRightMenu();
            runNoteSync();
        },
        initPropertor: function (prop) {
            runPropertor(prop);
        },
        intPwLength: function () {
            runPwLength();
        },
        init3DxLoader: function () {
            run3DxLoader();
        },
        initRandomUsername: function () {
            runRandomUsername();
        }
    };
}
();
/****DO-NoT-REMOVE-IT*#- ==============  END  ============== -#*/
